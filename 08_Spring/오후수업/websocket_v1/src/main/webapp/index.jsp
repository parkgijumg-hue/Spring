<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>STOMP WebSocket 채팅 애플리케이션</title>

    <!-- Bootstrap CSS for UI Styling -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <!-- STOMP.js 라이브러리: WebSocket 위에서 STOMP 프로토콜 사용 -->
    <script src="https://cdn.jsdelivr.net/npm/@stomp/stompjs@7.0.0/bundles/stomp.umd.min.js"></script>

    <style>
        /* 추가 스타일링 */
        .chat-container {
            max-height: 400px;
            overflow-y: auto;
            border: 1px solid #ddd;
            padding: 10px;
            margin-top: 15px;
        }

        .info-message {
            font-style: italic;
            color: #777;
        }

        .message-time {
            font-size: 0.8em;
            margin-right: 5px;
        }

        .connection-status {
            margin-top: 10px;
            font-weight: bold;
        }
    </style>
</head>
<body>
<div class="container">
    <!-- 페이지 헤더 -->
    <div class="page-header">
        <h1>🚀 Spring WebSocket + STOMP 프로토콜을 이용한 실시간 채팅</h1>
    </div>

    <!-- 연결 및 메시지 전송 컨트롤 영역 -->
    <div class="row">
        <!-- 연결 섹션 -->
        <div class="col-md-6">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title">🔌 WebSocket 연결</h3>
                </div>
                <div class="panel-body">
                    <form class="form-inline">
                        <div class="form-group">
                            <label for="name">사용자 이름:</label>
                            <input type="text"
                                   id="name"
                                   class="form-control"
                                   placeholder="이름을 입력하세요 (최대 20자)"
                                   maxlength="20"
                                   style="margin-left: 5px; margin-right: 10px;">
                        </div>

                        <!-- 연결/해제 버튼 -->
                        <button id="connect"
                                class="btn btn-success"
                                type="button"
                                title="WebSocket 연결을 시작합니다">
                            🔗 연결
                        </button>

                        <button id="disconnect"
                                class="btn btn-danger"
                                type="button"
                                disabled="disabled"
                                title="WebSocket 연결을 종료합니다"
                                style="margin-left: 5px;">
                            ❌ 끊기
                        </button>
                    </form>

                    <!-- 연결 상태 표시 -->
                    <div id="connection-status" class="connection-status">
                        🔴 연결 해제
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>

<!-- STOMP 클라이언트 JavaScript 로드 -->
<!--
    중요: 이 스크립트는 모든 HTML 요소가 로드된 후에 실행되어야 합니다.
    stomp.js 파일에는 STOMP 클라이언트 초기화 및 이벤트 처리 코드가 포함되어 있습니다.
-->
<script src="/resources/js/stomp.js"></script>


</body>
</html>