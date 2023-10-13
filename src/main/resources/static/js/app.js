let stompClient = null;

function connect() {
  const socket = new SockJS('/chat');
  stompClient = Stomp.over(socket);
  stompClient.connect({}, function (frame) {
    console.log('Connected: ' + frame);
    stompClient.subscribe('/topic/chat', function (message) {
      respondMessage(JSON.parse(message.body).content);
    });
  });
}
/*TODO : 사용자가 설정한 nickname 을 가져와서 보여줄 수 있을지 고민해보자.*/
function sendMessage() {
  const inputMessage = $('#input-message');
  const message = inputMessage.val();

  $(".direct-chat-messages").append(`
        <div class="direct-chat-msg">
            <div class="direct-chat-infos clearfix">
                <span class="direct-chat-name float-left">Me</span>
            </div>
            <div class="direct-chat-text">
                ${message}
            </div>
        </div>
    `);

  stompClient.send("/app/hello", {}, JSON.stringify({'content': $("#input-message").val()}));
  inputMessage.val("");
}

function respondMessage(message) {
  $(".direct-chat-messages").append(`
        <div class="direct-chat-msg right">
            <div class="direct-chat-infos clearfix">
                <span class="direct-chat-name float-right">Bot</span>
            </div>
            <div class="direct-chat-text">
                ${message}
            </div>
        </div>
    `);
}

$(function () {
  $("#chat-form").on('submit', function (e) {
    e.preventDefault();
    sendMessage();
  });
  $("#chat-form button").click(function(e) {
    sendMessage();
  });
});
