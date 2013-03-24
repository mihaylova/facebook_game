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
      
     $('#answer1').on 'click', =>
      @ws.send JSON.stringify({kind: 'answer', answer: $('#answer1').text(), button: '1'})
      
     $('#answer2').on 'click', =>
      @ws.send JSON.stringify({kind: 'answer', answer: $('#answer2').text(), button: '2'})
      
     $('#answer3').on 'click', =>
      @ws.send JSON.stringify({kind: 'answer', answer: $('#answer3').text(), button: '3'})
      
     $('#answer4').on 'click', =>
      @ws.send JSON.stringify({kind: 'answer', answer: $('#answer4').text(), button: '4'})

    this
    
  start: ->
    @ws = new WebSocket 'ws://localhost:9000/game/join'

    @ws.onerror = ->
      alert "WebSocket Error #{error}"
    
    @ws.onclose = =>
      @render {message: 'Connection closed'}

    @ws.onopen = =>
      @render {message: 'Connection opened'}

    @ws.onmessage = (e) =>
      data = JSON.parse e.data
      console.log data.kind

      switch data.kind
        when 'join', 'quit' then @render(data, 'users-table', @selectors.users_table)
        when 'start' then @render(data, 'message', @selectors.message)
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

    this
    
  stop: ->
    @ws.close()

  render: (data, tmpl_name = 'status', el = $('#status')) ->
    template = $("##{tmpl_name}-template").html()
    
    result = _.template(template, data)
    
    el.html result

  showCategory: (data) ->
    $('div.question').hide()
    $('#message-bet').show()
    $('#bet').show()
    $('#category').show()
    @render(data, 'category', @selectors.category)
    $('#bet').text(data.bet)
    $("#message").hide()
    members = data.members
    
    for member in members
      $("#member-#{member.uid} span.points").text(member.points)
    
  betting: (data) ->
     console.log 'betting'
     
     current_player = parseInt(data.user_on_turn)
     max_bet = parseInt(data.max_bet)
     uncall_bet = parseInt(data.user_uncall_bet)
     #current_player = parseInt(data.message)
     $('.betting').removeClass('betting')
     $("#member-#{current_player}").addClass('betting')
     $('input[name=raise]').attr("min", uncall_bet+1)
     $('input[name=raise]').attr("max", max_bet)
     $('span.bet').text(uncall_bet)
     
     if current_player == @user_id
       @selectors.bet_btn.show()
     else
       @selectors.bet_btn.hide()
       
  call_or_raise: (data) ->
     
     message = data.message
     user_uid = data.user_uid
     game_bet = data.game_bet
     user_points = data.user_points
     $('#message-bet').text(message)
     $("#member-#{user_uid} span.points").text(user_points)
     $('#bet').text(game_bet)
     
  fold: (data) ->
     
     message = data.message
     $('#message-bet').text(message)  
  
  win: (data) ->
    console.log 'win'
    $('#category').hide()
    
    $('.betting').removeClass('betting')
    @selectors.bet_btn.hide()
    message = data.message
    user_uid = data.user_uid
    user_points = data.user_points
    $('#message-bet').show()
    $('#message-bet').text(message)
    $("#member-#{user_uid} span.points").text(user_points)
    
  askQuestion: (data) ->
    console.log 'question'
    $('#message-bet').hide()
    $('#bet').hide()
    question = data.question
    answer1 = data.answer1
    answer2 = data.answer2
    answer3 = data.answer3
    answer4 = data.answer4
    members = data.members
   
    $('#category').hide()
    
    $('.betting').removeClass('betting')
    @selectors.bet_btn.hide()
    $('div.question').show()
    $('h4.question').text(question)
    $('#answer1').text(answer1)
    $('#answer2').text(answer2)
    $('#answer3').text(answer3)
    $('#answer4').text(answer4)
    $('#answer1').addClass('btn');
    $('#answer2').addClass('btn');
    $('#answer3').addClass('btn');
    $('#answer4').addClass('btn');
    $('#answer1').addClass('answer');
    $('#answer2').addClass('answer');
    $('#answer3').addClass('answer');
    $('#answer4').addClass('answer');
    $('div.question').removeClass('unactive')
    $('button.right').removeClass('right')
    $('button.check').removeClass('check')
   # @createTimer(16)
    
    #for member in members
      #if parseInt(member.uid) != @user_id
       #  $('div.question').addClass('unactive')
      #else
        # $('div.question').removeClass('unactive')
        
    if(@user_id in members)
      $('div.question').removeClass('unactive')
    else
      $('div.question').addClass('unactive')

  markAnswer: (data) ->
    console.log 'markanswer'
    button =  parseInt(data.button)
    $("#answer#{button}").addClass('check');
    $('#answer1').removeClass('btn');
    $('#answer2').removeClass('btn');
    $('#answer3').removeClass('btn');
    $('#answer4').removeClass('btn');
    $('#answer1').removeClass('answer');
    $('#answer2').removeClass('answer');
    $('#answer3').removeClass('answer');
    $('#answer4').removeClass('answer');
    $('div.question').addClass('unactive')
    
  message: (data) ->
    $('#message-bet').show()
    $('#message-bet').text(data.message)
    #$('div.question').hide()
    
  #Unchecked
  moreWinners: (data) ->
    $('#message-bet').show()
    $('#message-bet').text(data.message)
    members = data.members
    
    for member in members
      $("#member-#{member.uid} span.points").text(member.points)
       
    
    
    
  finishAnswering: (data) ->
    $('#answer1').removeClass('btn');
    $('#answer2').removeClass('btn');
    $('#answer3').removeClass('btn');
    $('#answer4').removeClass('btn');
    $('#answer1').removeClass('answer');
    $('#answer2').removeClass('answer');
    $('#answer3').removeClass('answer');
    $('#answer4').removeClass('answer');
    $('div.question').addClass('unactive')
    rigth_answer = data.message
    if $('#answer1').text()==rigth_answer
      $('#answer1').addClass('right')
    else if $('#answer2').text()==rigth_answer
      $('#answer2').addClass('right')
    else if $('#answer3').text()==rigth_answer
      $('#answer3').addClass('right')
    else if $('#answer4').text()==rigth_answer
      $('#answer4').addClass('right')
    
    
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
