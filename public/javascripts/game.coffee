class Game
  constructor: (user_id) ->
    @user_id = user_id
    
    @selectors = 
      users_table: $('#users-table')
      category: $('#category')
      message: $('#message')
      bet_btn: $('.bet-btn')
      
    $('#fold').on 'click', =>
      @ws.send JSON.stringify({kind: 'fold'})
        
    $('#raise').on 'click', =>
      @ws.send JSON.stringify({kind: 'raise', bet: $('input[name=raise]').val()})
        
    $('#call').on 'click', =>
      @ws.send JSON.stringify({kind: 'call'})
      
     $('#answerA').on 'click', =>
      @ws.send JSON.stringify({kind: 'answer', answer: $('#answerA').text(), button: 'A'})
      
     $('#answerB').on 'click', =>
      @ws.send JSON.stringify({kind: 'answer', answer: $('#answerB').text(), button: 'B'})
      
     $('#answerC').on 'click', =>
      @ws.send JSON.stringify({kind: 'answer', answer: $('#answerC').text(), button: 'C'})
      
     $('#answerD').on 'click', =>
      @ws.send JSON.stringify({kind: 'answer', answer: $('#answerD').text(), button: 'D'})
     
     $('#joker50').on 'click', =>
      @ws.send JSON.stringify({kind: 'joker50'})
     
     $('#joker_voice').on 'click', =>
      @ws.send JSON.stringify({kind: 'joker_voice'})


    this
    
  start: ->
    @ws = new WebSocket 'ws://localhost:9000/game/join'

    @ws.onerror = ->
      alert "WebSocket Error #{error}"
    
    @ws.onclose = =>
      @render {message: 'Connection closed'}

    @ws.onopen = =>
      #@render {message: 'Connection opened'}

    @ws.onmessage = (e) =>
      data = JSON.parse e.data
      console.log data.kind

      switch data.kind
        when 'join', 'quit' then @render(data, 'users-table', @selectors.users_table)
        when 'start' then @startGame (data)
        when 'category' then @showCategory(data)
        when 'user_on_turn' then @betting(data)
        when 'call_or_raise' then @call_or_raise(data)
        when 'fold' then @fold(data)
        when 'win' then @win(data)      
        when 'question' then @askQuestion(data) 
        when 'answer' then @markAnswer(data)
        when 'message' then @message(data)
        when 'finish_answering' then @finishAnswering(data)
        when 'more_winners' then @moreWinners(data)
        when 'mark_right_answer' then @markRightAnswer (data)
        when 'finish' then @finishGame(data)
        when 'out_of_points' then $('#message-out-of-points').text(data.message)
        when 'joker_50' then @joker (data)
        when 'update_points' then $("#member-#{@user_id} span.points").text(data.message)
        when 'joker_voice' then @joker_voice (data)

    this
    
  stop: ->
    @ws.close()

  render: (data, tmpl_name = 'status', el = $('#status')) ->
    template = $("##{tmpl_name}-template").html()
    
    result = _.template(template, data)
    
    el.html result
  
  joker_voice: (data) ->
    $('#choiceA').show()
    $('#choiceB').show()
    $('#choiceC').show()
    $('#choiceD').show()
    $('#choiceA').text("#{data.choice_answer1}%")
    $('#choiceB').text("#{data.choice_answer2}%")
    $('#choiceC').text("#{data.choice_answer3}%")
    $('#choiceD').text("#{data.choice_answer4}%")
    $('div.jokers').hide()
    
    
  joker: (data) ->
    answer1 = data.answer1
    answer2 = data.answer2
    answer3 = data.answer3
    answer4 = data.answer4
    $('#answerA').text(answer1)
    $('#answerB').text(answer2)
    $('#answerC').text(answer3)
    $('#answerD').text(answer4)
    $('div.jokers').hide()  
    
   
    
    
    
  finishGame: (data) ->
    $('#choiceA').hide()
    $('#choiceB').hide()
    $('#choiceC').hide()
    $('#choiceD').hide()
    $('div.jokers').hide()
    $('#timer').hide()
    $('#timer1').hide()
    $('#timer2').hide()
    $('#timer3').hide()
    $('#timer4').hide()
    $('#finish-message').show()
    $('#finish-message').text(data.message)
    $('#message-out-of-points').hide()
    $('#message').hide()
    $("div.user-answer").hide()
    $('div.question').hide()
    $('#bet').hide()
    $('#category').hide()
    $('.betting').removeClass('betting')
    @selectors.bet_btn.hide()
    
    
  startGame: (data) ->
    $('#message').show()
    $('#message').text(data.message)
    
    
  showCategory: (data) ->
    $('#choiceA').hide()
    $('#choiceB').hide()
    $('#choiceC').hide()
    $('#choiceD').hide()
    $("div.user-answer").hide()
    $('div.jokers').hide()
    $('div.question').hide()
    $('#message').hide()
    $('#bet').show()
    $('#category').show()
    @render(data, 'category', @selectors.category)
    $('#bet').text("Залог: #{data.bet}")
    
    members = data.members
    
    for member in members
      
      $("#member-#{member.uid} span.points").text(member.points)
    
  betting: (data) ->
     console.log 'betting'
    # $('#message').hide()
     current_player = parseInt(data.user_on_turn)
     max_bet = parseInt(data.max_bet)
     uncall_bet = parseInt(data.user_uncall_bet)
     slot = data.slot
     $("#timer#{slot}").show()
     $("#timer#{slot}").pietimer('start')
     $("#timer#{slot}").pietimer(
      timerSeconds: 7,
      color: '#234',
      fill: false,
      showPercentage: true,
      callback: ->
        $("#timer#{slot}").hide()
        $("#timer#{slot}").pietimer('reset'))
     
     $('.betting').removeClass('betting')
     $("#member-#{current_player}").addClass('betting')
     $('input[name=raise]').attr("min", uncall_bet+1)
     $('input[name=raise]').attr("value", uncall_bet+1)
     $('input[name=raise]').attr("max", max_bet)
     $('span.bet').text(uncall_bet)
     
     if current_player == @user_id
       @selectors.bet_btn.show()
     else
       @selectors.bet_btn.hide()
       
  call_or_raise: (data) ->
     $('#timer1').hide()
     $('#timer2').hide()
     $('#timer3').hide()
     $('#timer4').hide()
     $('#message').show()
     message = data.message
     user_uid = data.user_uid
     game_bet = data.game_bet
     user_points = data.user_points
     $('#message').text(message)
     $("#member-#{user_uid} span.points").text(user_points)
     $('#bet').text("Залог: #{game_bet}")
     
     
  fold: (data) ->
     $('#timer1').hide()
     $('#timer2').hide()
     $('#timer3').hide()
     $('#timer4').hide()
     $('#message').show()
     message = data.message
     $('#message').text(message)  
  
  win: (data) ->
    console.log 'win'
    $('#category').hide()
    $('#bet').hide()
    $('.betting').removeClass('betting')
    @selectors.bet_btn.hide()
    message = data.message
    user_uid = data.user_uid
    user_points = data.user_points
    $('#message').show()
    $('#message').text(message)
    $("#member-#{user_uid} span.points").text(user_points)
    
  askQuestion: (data) ->
    hasjoker=parseInt(data.hasjoker)
    $('#choiceA').hide()
    $('#choiceB').hide()
    $('#choiceC').hide()
    $('#choiceD').hide()
    console.log 'question'
    $('#message').show()
    $('div.jokers').show()
    $("div.user-answer").hide()
    $('#timer').show()
    $('#timer').pietimer('start')
    $('#timer').pietimer(
      timerSeconds: 15,
      color: '#234',
      fill: false,
      showPercentage: true,
      callback: ->
        $('#timer').hide()
        $('#timer').pietimer('reset'))
        
      
          
            
    if(hasjoker==1)
      $('#joker_voice').show()
    else
      $('#joker_voice').hide()     
    $('#bet').hide()
    question = data.question
    answer1 = data.answer1
    answer2 = data.answer2
    answer3 = data.answer3
    answer4 = data.answer4
    members = data.members
    members_out_of_coins = data.members_out_of_coins
    bet = data.bet
    $('#category').hide()
    
    $('.betting').removeClass('betting')
    @selectors.bet_btn.hide()
    $('div.question').show()
    $('h4.question').text(question)
    $('#answerA').text(answer1)
    $('#answerB').text(answer2)
    $('#answerC').text(answer3)
    $('#answerD').text(answer4)
    
    
    
    
    $('button.right').removeClass('right')
    $('button.check').removeClass('check')
   # @createTimer(16)

        
        
        
    if(@user_id in members)
      $('div.question').removeClass('unactive')
      
      $('#answerA').addClass('btn');
      $('#answerB').addClass('btn');
      $('#answerC').addClass('btn');
      $('#answerD').addClass('btn');
      $('#answerA').addClass('answer');
      $('#answerB').addClass('answer');
      $('#answerC').addClass('answer');
      $('#answerD').addClass('answer');
      $('#message').text("Играете за #{bet} точки")
      if(@user_id in members_out_of_coins)
        $('div.jokers').hide()
    else
      $('div.question').addClass('unactive')
      $('#answerA').removeClass('btn');
      $('#answerB').removeClass('btn');
      $('#answerC').removeClass('btn');
      $('#answerD').removeClass('btn');
      $('#answerA').removeClass('answer');
      $('#answerB').removeClass('answer');
      $('#answerC').removeClass('answer');
      $('#answerD').removeClass('answer');
      $('#message').text("Не можете да отговаряте")
      $('div.jokers').hide()
      

  markAnswer: (data) ->
    $('#choiceA').hide()
    $('#choiceB').hide()
    $('#choiceC').hide()
    $('#choiceD').hide()
    console.log 'markanswer'
    $('div.jokers').hide()
    button =  data.message
    $("#answer#{button}").addClass('check');
    $('#answerA').removeClass('btn');
    $('#answerB').removeClass('btn');
    $('#answerC').removeClass('btn');
    $('#answerD').removeClass('btn');
    $('#answerA').removeClass('answer');
    $('#answerB').removeClass('answer');
    $('#answerC').removeClass('answer');
    $('#answerD').removeClass('answer');
    $('div.question').addClass('unactive')
    
  message: (data) ->
    $('#message').show()
    $('#message').text(data.message)
    #$('div.question').hide()
    
  #Unchecked
  moreWinners: (data) ->
    $('#message').show()
    $('#message').text(data.message)
    members = data.members
    
    for member in members
      $("#member-#{member.uid} span.points").text(member.points)
       
    
    
  markRightAnswer: (data) ->
    rigth_answer = data.message
    if $('#answerA').text()==rigth_answer
      $('#answerA').addClass('right')
    else if $('#answerB').text()==rigth_answer
      $('#answerB').addClass('right')
    else if $('#answerC').text()==rigth_answer
      $('#answerC').addClass('right')
    else if $('#answerD').text()==rigth_answer
      $('#answerD').addClass('right')
      
  finishAnswering: (data) ->
    $('#choiceA').hide()
    $('#choiceB').hide()
    $('#choiceC').hide()
    $('#choiceD').hide()
    $('#message').hide()
    $('div.jokers').hide()
    $('#answerA').removeClass('btn');
    $('#answerB').removeClass('btn');
    $('#answerC').removeClass('btn');
    $('#answerD').removeClass('btn');
    $('#answerA').removeClass('answer');
    $('#answerB').removeClass('answer');
    $('#answerC').removeClass('answer');
    $('#answerD').removeClass('answer');
    $('div.question').addClass('unactive')
    members = data.members
    for member in members
      time = parseInt(member.time)
      if member.answer != null
        $("#member-#{member.uid} div.user-answer").show()
        $("#member-#{member.uid} div.user-answer span.answer").text(member.answer)
        if time<10
          $("#member-#{member.uid} div.user-answer span.time").text("time: 00:0#{time}")
        else if time>9
          $("#member-#{member.uid} div.user-answer span.time").text("time: 00:#{time}") 
        
      
    
    
    
  createTimer: (sec) ->
    
    counter=setInterval(@timer(sec), 1000)

  timer: (sec)->
    sec = sec-1
    if sec<=0
      clearInterval(counter)
    if sec>9
      $('#timer').text("00:#{sec}")
    if sec<=9
      $('#timer').text("00:0#{sec}")
     
      
      

window.Game = Game

        #$('input[name=raise]').val('dsa')
        #$('input[name=raise]').val()
        #$('input[name=raise]').attr.("min", 231313)
