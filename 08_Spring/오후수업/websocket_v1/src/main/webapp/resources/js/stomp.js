/*
 * 🔄 전체 STOMP 메시지 흐름 요약:
 *
 * 1️⃣ 연결 단계:
 *    사용자 이름 입력 → connect() → stompClient.activate()
 *    → WebSocket 연결 → onConnect 콜백 → 토픽 구독 → 입장 메시지 발행
 *
 * 2️⃣ 메시지 전송 단계:
 *    메시지 입력 → sendMessage() → stompClient.publish("/app/chat")
 *    → ChatController.chat() → @SendTo("/topic/chat") → 모든 구독자에게 브로드캐스트
 *
 * 3️⃣ 메시지 수신 단계:
 *    서버 브로드캐스트 → subscribe 콜백 → JSON 파싱 → showMessage() → 화면 출력
 *
 * 4️⃣ 연결 해제 단계:
 *    disconnect() → stompClient.deactivate() → 연결 종료 → UI 상태 변경
 */

/* ==================== STOMP 클라이언트 초기화 ==================== */

/**
 * STOMP 클라이언트 생성
 * - StompJs 라이브러리를 사용하여 WebSocket 기반 STOMP 클라이언트 생성
 * - 서버의 WebSocketConfig에서 설정한 엔드포인트와 연결
 */
// 동적으로 현재 페이지의 호스트를 사용하여 WebSocket URL 생성
// 이렇게 하면 localhost, 내부IP, 도메인 등 어떤 주소로 접속해도 WebSocket 연결이 가능
const currentHost = window.location.host; // 현재 페이지의 호스트:포트 (예: 192.168.1.100:8080)
const wsProtocol = window.location.protocol === 'https:' ? 'wss:' : 'ws:'; // HTTPS면 wss, HTTP면 ws
const webSocketURL = `${wsProtocol}//${currentHost}/chat-app`;

console.log('🔗 WebSocket 연결 URL:', webSocketURL);

const stompClient = new StompJs.Client({
    brokerURL: webSocketURL   // 동적 생성된 WebSocket URL 사용
});


/**
 * WebSocket 연결 에러 핸들링
 * - 네트워크 문제, 서버 다운 등으로 인한 WebSocket 레벨 오류 처리
 */
stompClient.onWebSocketError = (error) => {
    console.error('🔴 WebSocket 연결 오류:', error);
    console.error('🔍 연결 시도 URL:', webSocketURL);
    console.error('🔍 현재 호스트:', currentHost);
    alert(`서버 연결에 실패했습니다.\n연결 URL: ${webSocketURL}\n네트워크 상태를 확인해주세요.`);
    setConnected(false);  // UI 상태를 연결 해제 상태로 변경
};


/**
 * STOMP 프로토콜 에러 핸들링
 * - STOMP 레벨에서 발생하는 오류 처리 (인증 실패, 권한 부족 등)
 */
stompClient.onStompError = (frame) => {
    console.error('🔴 STOMP 프로토콜 오류:', frame.headers['message']);
    console.error('🔍 상세 정보:', frame.body);
    alert('메시징 프로토콜 오류가 발생했습니다.');
};




/* ==================== 연결 성공 시 구독 설정 ==================== */

/**
 * WebSocket 연결 성공 시 실행되는 콜백 함수
 * - 연결 완료 후 필요한 토픽들을 구독
 * - 자동으로 입장 메시지 전송
 */
stompClient.onConnect = (frame) => {
    console.log('✅ WebSocket 연결 성공:', frame);
    setConnected(true);  // UI를 연결된 상태로 변경

    // 🔔 입장 알림 토픽 구독
    // - 다른 사용자들의 입장 알림을 받기 위한 구독
    // - ChatController.greeting() 메서드의 @SendTo("/topic/greetings")와 연결
    stompClient.subscribe('/topic/greetings', (greeting) => {
        console.log('📨 입장 알림 수신:', greeting.body);

        // JSON 문자열을 JavaScript 객체로 파싱
        const message = JSON.parse(greeting.body);

        // 입장 메시지를 화면에 표시 (GreetingMessage.name 사용)
        console.log(`${message.name}님이 입장했습니다.`);
    });



    // 🚀 연결 즉시 입장 메시지 자동 전송
    // - 연결 성공과 동시에 다른 사용자들에게 입장을 알림
    const name = document.getElementById('name').value;
    stompClient.publish({
        destination: '/app/hello',              // ChatController의 @MessageMapping("/hello")로 전송
        body: JSON.stringify({name: name})      // GreetingMessage 형태로 직렬화
        // JSON.stringify(...) :  JavaScript 객체를 JSON 형식의 문자열로 변환
    });
};



/* ==================== 핵심 기능 함수들 ==================== */

/**
 * WebSocket 연결 시작
 * - 사용자 이름 유효성 검사 후 연결 시도
 * - 연결 성공 시 onConnect 콜백이 자동 실행됨
 */
function connect() {
    const nameInput = document.getElementById('name');
    const userName = nameInput.value.trim();

    // 입력 유효성 검사
    if (!userName) {
        alert('이름을 입력해주세요.');
        nameInput.focus();
        return;
    }

    // 이름 길이 제한 (선택적)
    if (userName.length > 20) {
        alert('이름은 20자 이내로 입력해주세요.');
        nameInput.focus();
        return;
    }

    console.log('🔄 WebSocket 연결 시도 중...', userName);

    // STOMP 클라이언트 활성화 (연결 시작)
    // - 성공 시: onConnect 콜백 실행
    // - 실패 시: onWebSocketError 또는 onStompError 콜백 실행
    stompClient.activate();
}


/**
 * WebSocket 연결 종료
 * - 정상적인 연결 해제 처리
 * - UI 상태를 연결 해제 상태로 변경
 */
function disconnect() {
    console.log('🔄 연결 해제 중...');

    // STOMP 클라이언트 비활성화
    stompClient.deactivate();

    // UI 상태 변경
    setConnected(false);

    console.log('✅ 연결이 정상적으로 해제되었습니다.');
}


/**
 * 채팅 메시지 전송
 * - 입력된 메시지를 서버로 발행
 * - 서버에서 처리 후 모든 구독자에게 브로드캐스트
 */
function sendMessage() {
    const name = document.getElementById('name').value;  // 이름 input
    const contentInput = document.getElementById('content'); // 메시지 input
    const content = contentInput.value.trim(); // 좌우 공백 제거된 메시지

    // 메시지 유효성 검사
    if (!content) {
        alert('메시지를 입력해주세요.');
        contentInput.focus();
        return;
    }

    // 메시지 길이 제한 (클라이언트 측 검증)
    if (content.length > 500) {
        alert('메시지는 500자 이내로 입력해주세요.');
        return;
    }

    console.log('📤 메시지 전송:', { name, content });

    // 서버로 메시지 발행
    // - destination: ChatController의 @MessageMapping("/chat")와 매칭
    // - body: ChatMessage 형태의 JSON 문자열
    stompClient.publish({
        destination: '/app/chat',
        body: JSON.stringify({
            name: name,
            content: content
        })
    });

    // 입력 필드 초기화 및 포커스 이동
    contentInput.value = '';
    contentInput.focus();

    console.log('✅ 메시지 전송 완료');
}



/* ==================== UI 관리 함수들 ==================== */

/**
 * 연결 상태에 따른 UI 업데이트
 * - 버튼 활성화/비활성화
 * - 채팅 영역 초기화
 *
 * @param {boolean} connected - 연결 상태 (true: 연결됨, false: 연결 해제)
 */
function setConnected(connected) {
    // 연결 상태에 따른 버튼 활성화 제어
    document.getElementById('connect').disabled = connected;        // 연결 시 연결 버튼 비활성화
    document.getElementById('disconnect').disabled = !connected;    // 연결 시 끊기 버튼 활성화

  

    // 연결 상태 표시 (선택적)
    const statusElement = document.getElementById('connection-status');
    if (statusElement) {
        statusElement.textContent = connected ? '🟢 연결됨' : '🔴 연결 해제';
        statusElement.className = connected ? 'text-success' : 'text-danger';
    }
}


/* ==================== 이벤트 핸들러 등록 ==================== */

/**
 * DOM 로딩 완료 후 이벤트 핸들러 설정
 * - 모든 HTML 요소가 로드된 후 실행
 * - 버튼 클릭, 키보드 입력 등의 이벤트 리스너 등록
 */
window.addEventListener("DOMContentLoaded", (event) => {

    console.log('========== STOMP 채팅 클라이언트 초기화 시작 ==========');


    // 버튼 클릭 이벤트 등록

    // 연결 버튼
    document.getElementById('connect').addEventListener('click', (e) => {
        e.preventDefault();  // 기본 동작 방지
        connect();
    });

    // 끊기 버튼
    document.getElementById('disconnect').addEventListener('click', (e) => {
        e.preventDefault();
        disconnect();
    });


});




/* ==================== 페이지 종료 시 정리 ==================== */

/**
 * 페이지 새로고침 또는 종료 시 연결 정리
 * - 브라우저 종료나 페이지 이동 시 WebSocket 연결을 정상적으로 해제
 */
window.addEventListener('beforeunload', (event) => {
    if (isConnected()) {
        console.log('🔄 페이지 종료 - WebSocket 연결 정리 중...');
        stompClient.deactivate();
    }
});