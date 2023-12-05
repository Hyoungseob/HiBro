$(document).ready(function () {
	// 비디오 요소와 버튼 요소들을 선택하여 변수에 할당
	const videoPlayer = $("#videoPlayer")[0]; // 비디오 요소 가져오기
	const playPauseButton = $("#vdoc2"); // 재생/일시정지 버튼 가져오기
	const muteUnmuteButton = $("#vdoc3"); // 음소거/음소거 해제 버튼 가져오기

	// 재생/일시정지 버튼 클릭 시 실행될 함수
	function togglePlayPause() {
		if (videoPlayer.paused) {
			videoPlayer.play(); // 비디오 재생
			playPauseButton.text("일시정지"); // 버튼 텍스트 변경
		} else {
			videoPlayer.pause(); // 비디오 일시 정지
			playPauseButton.text("재생"); // 버튼 텍스트 변경
		}
	}

	// 음소거/음소거 해제 버튼 클릭 시 실행될 함수
	function toggleMuteUnmute() {
		if (videoPlayer.muted) {
			videoPlayer.muted = false; // 음소거 해제
			muteUnmuteButton.text("음소거"); // 버튼 텍스트 변경
		} else {
			videoPlayer.muted = true; // 음소거
			muteUnmuteButton.text("음소거 해제"); // 버튼 텍스트 변경
		}
	}

	// 재생/일시정지 버튼 클릭 이벤트에 함수 연결
	playPauseButton.on("click", togglePlayPause);

	// 음소거/음소거 해제 버튼 클릭 이벤트에 함수 연결
	muteUnmuteButton.on("click", toggleMuteUnmute);

	// #vdoc2와 #vdoc3 버튼을 클릭할 때의 동작을 하나의 함수로 처리
	playPauseButton.add(muteUnmuteButton).on("click", function () {
		// 현재 클릭한 요소의 "clicked" 클래스를 추가하거나 제거하여 토글
		$(this).toggleClass("clicked");
	});
});
/*ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ*/
$(document).ready(function () {
	const videos = [
		{
			source: "video/비공식작전.mp4",
			title: "비공식작전",
			content: "하정우x주지훈+김성훈 감독!<br>한국영화 카체이싱 원탑! 절찬상영중!",
		},
		{
			source: "video/보호자.mp4",
			title: "보호자",
			content: "폭주하는 성난 액션VS광기의 빌런즈<br>압도적 반응! 지금 바로 예매 GO>",
		},
		{
			source: "video/스파이 코드명 포춘.mp4",
			title: "스파이 코드명 포춘",
			content: "넥스트 레벨'제이슨 스타뎀'이 온다<br>8월30일 스파이 포춘 작전개시!",
		},
		{
			source: "video/런닝맨.mp4",
			title: "런닝맨: 리벤져스",
			content: "전설의 아이템 슈퍼벨트를 찾아라!<br>8월10일, 레이스 스타트!",
		},
		{
			source: "video/메가로돈2.mp4",
			title: "메가로돈2",
			content: "제이슨 스타뎀VS메가로돈 리매치!<br>8월 15일 극장 대개봉",
		},
		{
			source: "video/달짝지근해.mp4",
			title: "달짝지근해",
			content: "올여름 유해진이 말아주는 코믹로맨스<br>8월 15일, 가장 유쾌한 흥행복병!",
		},
		{
			source: "video/콘크리트 유토피아.mp4",
			title: "콘크리트 유토피아",
			content: "극장가를 압도할 강렬한 기대작<br>8월 9일, 큰 거 온다!",
		},
	];

	// videos 배열에서 무작위 비디오 선택
	const randomVideo = videos[Math.floor(Math.random() * videos.length)];

	// videoPlayer 요소의 소스(src)를 선택된 비디오의 소스로 설정
	$("#videoPlayer").attr("src", randomVideo.source);
	// videoTitle 요소의 텍스트를 선택된 비디오의 제목으로 설정
	$("#videoTitle").text(randomVideo.title);
	// videoContent 요소의 HTML 내용을 선택된 비디오의 내용으로 설정
	$("#videoContent").html(randomVideo.content);
});
/*ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ*/
// 슬라이더 요소와 좌우 버튼을 선택
const slider = document.querySelector("#movie_list");
const prevButton = document.getElementById("prevButton");
const nextButton = document.getElementById("nextButton");
// 슬라이더 관련 설정
const slideWidth = 170 * 5; // 이미지 너비 * 한 번에 보여줄 이미지 개수
const slideMargin = 32 * 5; // 이미지 간격 * 한 번에 보여줄 이미지 개수
const slideAmount = 5; // 한 번에 표시되는 이미지 개수
let position = 0; // 슬라이더의 현재 위치 초기화
// 슬라이더가 최대 범위에 도달하는 지점
const maxPosition = -(slideWidth + slideMargin) * (Math.ceil(slider.children.length / slideAmount) - 1);
// 드래그 관련 변수 초기화
let isDragging = false;
let dragStartX = 0;
let dragStartPos = 0;

// 버튼 상태를 업데이트하는 함수
function updateButtonStatus() {
	// 현재 슬라이더 위치가 맨 처음인 경우 이전 버튼을 비활성화하고 숨김 클래스 추가
	if (position === 0) {
		prevButton.classList.add("hidden"); // 이전 버튼에 숨김 클래스 추가
	} else {
		prevButton.classList.remove("hidden"); // 이전 버튼의 숨김 클래스 제거
	}
	// 현재 슬라이더 위치가 최대 범위에 도달한 경우 다음 버튼을 숨김 클래스를 추가하거나 제거하여 비활성화 또는 활성화
	nextButton.classList.toggle("hidden", position === maxPosition);
}

// 이전 버튼과 다음 버튼에 클릭 이벤트 추가
prevButton.addEventListener("click", slideLeft);
nextButton.addEventListener("click", slideRight);
// 왼쪽으로 슬라이드하는 함수
function slideLeft() {
	position += slideWidth + slideMargin; // 슬라이더 위치를 왼쪽으로 이동
	position = Math.min(position, 0); // 위치가 음수가 되지 않도록 조정
	slider.style.transform = `translateX(${position}px)`; // 슬라이더의 위치를 변경
	updateButtonStatus(); // 버튼 상태 업데이트
}
// 오른쪽으로 슬라이드하는 함수
function slideRight() {
	position -= slideWidth + slideMargin; // 슬라이더 위치를 오른쪽으로 이동
	position = Math.max(position, maxPosition); // 위치가 최대 범위를 넘어가지 않도록 조정
	slider.style.transform = `translateX(${position}px)`; // 슬라이더의 위치를 변경
	updateButtonStatus(); // 버튼 상태 업데이트
}
// 초기 버튼 상태 업데이트
updateButtonStatus();

// 마우스 이벤트 리스너 추가
document.addEventListener("mousedown", handleDragStart);
document.addEventListener("mousemove", handleDrag);
document.addEventListener("mouseup", handleDragEnd);
// 드래그 시작 처리
function handleDragStart(event) {
	const target = event.target;
	// 만약 드래그가 슬라이더 요소 내에서 시작되었다면 실행
	if (slider.contains(target)) {
		event.preventDefault(); // 기본 드래그 동작 방지
		isDragging = true; // 드래그 중 상태 설정
		dragStartX = event.clientX; // 드래그 시작 x 좌표 저장
		dragStartPos = position; // 드래그 시작 시 슬라이더 위치 저장
	}
}

// 드래그 중 처리
function handleDrag(event) {
	if (isDragging) {
		const offsetX = event.clientX - dragStartX; // 현재 마우스 위치에서 드래그 시작 지점까지의 거리 계산
		position = dragStartPos + offsetX; // 슬라이더의 새로운 위치 계산
		position = Math.min(Math.max(position, maxPosition), 0); // 위치를 최대값과 최소값 사이로 제한
		slider.style.transform = `translateX(${position}px)`; // 슬라이더 위치 업데이트
		updateButtonStatus(); // 버튼 상태 업데이트
	}
}

// 드래그 종료 처리
function handleDragEnd() {
	isDragging = false; // 드래그 상태를 비활성화하여 드래그 중이 아님을 표시
	slider.style.transition = "transform 0.3s ease-out"; // 슬라이더 이동에 트랜지션을 추가하여 부드럽게 이동하도록 설정
	slider.style.transform = `translateX(${position}px)`; // 슬라이더 위치 업데이트
	updateButtonStatus(); // 버튼 상태 업데이트
}

/*ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ*/
// jQuery를 사용하여 이미지 변경
$(document).ready(function () {
	// 현재 링크를 저장하는 변수 초기화
	let currentLink = "";
	// 이미지 아이템과 메인 이미지 요소 선택
	const imageItems = $(".spchList");
	const mainImage = $("#spchImg");

	// 이미지 아이템에 마우스 진입 이벤트 핸들러 추가
	// 리스트 아이템에 마우스 호버 시 해당 아이템의 링크 값을 저장
	imageItems.hover(function () {
		currentLink = $(this).find("a").attr("href"); // 현재 아이템의 링크 값을 가져와 currentLink 변수에 저장
		// 마우스 진입한 이미지 아이템의 데이터 속성에서 이미지 이름 가져오기
		const imageName = $(this).data("image");
		// 메인 이미지의 속성 변경 (src와 alt)
		mainImage.attr({
			src: imageName,
			alt: $(this).text(),
		});
		// 특정 컨텐츠를 클릭할 때 실행되는 이벤트 처리
		$("#specialHall_content").click(function () {
			const defaultLink = "http://www.cgv.co.kr/theaters/special/defaultDetailNew.aspx?idx=7"; // 기본 링크
			const finalLink = currentLink == "" ? defaultLink : currentLink; // 만약 현재 링크 값이 비어있으면 기본 링크를 사용하고, 그렇지 않으면 현재 링크를 사용
			window.location.href = finalLink; // 최종 링크로 페이지 이동
		});
	});
});
