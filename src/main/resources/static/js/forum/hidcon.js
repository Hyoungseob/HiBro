$(document).ready(function () {
	// 컨텐츠와 "위로 가기" 버튼 요소를 선택
	const $content = $("#ticketing");
	const $gotop = $("#gotop");

	// 스크롤 위치가 일정 거리 이상인 경우의 임계값
	const scrollThreshold = 209;

	// 요소들의 가시성을 업데이트하는 함수
	function updateVisibility() {
		// 스크롤 위치가 임계값 이상인 경우
		const isScrolledEnough = $(window).scrollTop() >= scrollThreshold;

		// "위로 가기" 버튼의 투명도 조정
		$gotop.css("opacity", isScrolledEnough ? 1 : 0);

		// 컨텐츠 영역의 투명도와 위치를 조정
		if (isScrolledEnough) {
			$content.animate(
				{
					right: "58px",
					opacity: 1,
				},
				5
			); // 0.5초 동안 애니메이션
		} else {
			$content.css({
				right: "",
				opacity: 0,
			}); // 바로 사라지도록 스타일 변경
		}
	}

	// 스크롤 이벤트 리스너 등록
	$(window).scroll(function () {
		updateVisibility(); // 요소들의 가시성 업데이트
	});

	// 초기 페이지 로딩 시 요소들의 가시성을 설정
	updateVisibility();
});
