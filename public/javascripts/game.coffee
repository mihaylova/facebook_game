class Game
  constructor: (user_id) ->
    @user_id = user_id
    
    @selectors = 
      users_table: $('#users-table')
      category: $('#category')
      message: $('#message')
      bet_btn: $('.bet-btn')
      button_example: $('#users-table')

    @selectors.button_example.delegate 'div span', 'click', =>
      @ws.send JSON.stringify({kind: 'message', text: 'Foooo!'})

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

      switch data.kind
        when 'join', 'quit' then @render(data, 'users-table', @selectors.users_table)
        when 'question' then console.log(data.message)
        when 'start' then @render(data, 'message', @selectors.message)
        when 'category' then @showCategory(data)
        when 'user_on_turn' then @betting(data)
        when 'message' then console.log("User msg: #{data.message}")        

    this
    
  stop: ->
    @ws.close()

  render: (data, tmpl_name = 'status', el = $('#status')) ->
    template = $("##{tmpl_name}-template").html()
    
    result = _.template(template, data)
    
    el.html result

  showCategory: (data) ->
    @render(data, 'category', @selectors.category)
    
    $("#message").hide()
    
  betting: (data) ->
     current_player = parseInt(data.message)
     
     $('.betting').removeClass('betting')
     $("#member-#{current_player}").addClass('betting')

     if current_player == @user_id
       @selectors.bet_btn.show()
     else
       @selectors.bet_btn.hide()

window.Game = Game

