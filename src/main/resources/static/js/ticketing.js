$(document).ready(function () {
	let selectedLi = null; // 현재 선택된 li 요소
	let hidcon2LiClicked = false; // '.hidcon2 > ul > li' 클릭 여부
	let con1LiClicked = false; // '#con1 > ul > li' 클릭 여부

	// con1의 직계 자손인 ul의 직계 자손인 li 클릭 이벤트 처리
	$("#con1 > ul > li").click(function () {
		const $this = $(this);
		// 선택한 li의 배경색 변경
		$this.css("background-color", "#ccc");

		// 선택한 li를 제외한 다른 li의 배경색 원래대로 변경
		$this.siblings().css("background-color", "");
		con1LiClicked = true; // '#con1 > ul > li' 클릭 여부 업데이트

		// con1과 .hidcon2 > ul > li 둘 다 클릭되었을 때에만 #dlatl2 표시
		if (con1LiClicked && hidcon2LiClicked) {
			$("#dlatl2").css("display", "block"); // #dlatl2 표시
		}
	});

	// .hidcon2 > ul > li 클릭하면 배경색 변경
	$(".hidcon2 > ul > li").click(function () {
		const $this = $(this);
		// 선택한 li의 배경색 변경
		$this.css("background-color", "#ccc");

		// 선택한 li를 제외한 다른 li의 배경색 원래대로 변경
		$this.siblings().css("background-color", "");
		hidcon2LiClicked = true; // '.hidcon2 > ul > li' 클릭 여부 업데이트

		// con1과 .hidcon2 > ul > li 둘 다 클릭되었을 때에만 #dlatl2 표시
		if (con1LiClicked && hidcon2LiClicked) {
			$("#dlatl2").css("display", "block"); // #dlatl2 표시
		}
	});

	// con2의 직계 자손인 ul의 직계 자손인 li 클릭 이벤트 처리
	$("#con2 > ul > li").click(function () {
		const $this = $(this);
		const $subMenu = $this.find(".hidcon2");

		if (selectedLi && selectedLi !== this) {
			$(selectedLi).css("background-color", "").find(".hidcon2").css("display", "none");
			$(".hidcon2 > ul > li").css("background-color", ""); // '.hidcon2 > ul > li'의 배경색 초기화
			$("#dlatl2").css("display", "none"); // #dlatl2 숨김
		}

		if (selectedLi === this) {
			// 이미 선택된 li를 다시 클릭한 경우, 선택 유지
			$subMenu.css("display", "block");
		} else {
			$subMenu.css("display", "block");
			$this.css("background-color", "#ccc");
			selectedLi = this; // 현재 선택된 li 업데이트
		}
	});

	// #dlatl2의 ul li 클릭하면 다른 div의 배경색 변경
	$("#con3 ul li").click(function () {
		$("#con3 ul li").css("background-color", ""); // 모든 li 배경색 초기화
		$(this).css("background-color", "#ccc"); // 클릭한 li 배경색 변경
		$("#goseatsel").css("background-color", "red");
		dlatl2LiClicked = true;
	});

	$("#goseatsel").click(function () {
		if ($("#goseatsel").css("background-color") === "rgb(255, 0, 0)") {
			$("#goseatsel").css("display", "none");
			$("#step1").css("display", "none");
			$("#step2").css("display", "block");
			$("#backmovsel").css("display", "block");
			$("#gopay").css("display", "block");
			$("#step3").css("display", "none");
		}
	});
	$("#backmovsel").click(function () {
		$("#con4").append($("#goseatsel"));
		$("#goseatsel").css("display", "block");
		$("#step1").css("display", "block");
		$("#backmovsel").css("display", "none");
		$("#step2").css("display", "none");
		$("#gopay").css("display", "none");
		$("#step3").css("display", "none");
	});
	$("#gopay").click(function () {
		$("#con4").prepend($("#goseatsel"));
		if ($("#gopay").css("background-color") === "rgb(255, 0, 0)") {
			$("#gopay").css("display", "none");
			$("#step2").css("display", "none");
			$("#step3").css("display", "block");
			$("#goseatsel").css("display", "block");
			$("#backmovsel").css("display", "none");
		}
	});
});
/*ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ*/
$(document).ready(function () {
	// 좌석 가격 정보 설정
	const prices = {
		adult: 15000, // 성인 가격
		youth: 12000, // 청소년 가격
		senior: 7000, // 노인 가격
		special: 5000, // 우대 가격
	};
	let selectedSeats = []; // 선택된 좌석 배열
	let prev = {};

	// 인원 선택 변경 시 처리 함수
	$("select")
		.focus(function () {
			// 포커스가 들어갈 때 이전 값을 저장
			prev[$(this).attr("id")] = $(this).val();
		})
		.change(function () {
			let sum = 0;

			// 선택한 인원 수 계산
			$("select").each(function () {
				sum += parseInt($(this).val());
			});

			if (sum > 8) {
				// 경고창 띄우기
				alert("총 선택된 인원은 8명을 초과할 수 없습니다.");
				$("select").each(function () {
					const id = $(this).attr("id");
					$(this).val(prev[id]); // 이전 값으로 복원
				});
			} else if (sum < $(".selectedSeat").length) {
				// 선택된 좌석 수가 최대 선택 가능 수를 초과하는 경우
				alert("선택된 좌석 수를 다시 확인해주세요.");
				$("select").each(function () {
					const id = $(this).attr("id");
					$(this).val(prev[id]); // 이전 값으로 복원
				});
			} else {
				// 현재 값 저장
				$("select").each(function () {
					const id = $(this).attr("id");
					prev[id] = $(this).val(); // 현재 값을 prev 객체에 저장
				});
				countSeatPrice(); // 좌석 가격 및 결제 버튼 상태 업데이트
				// 콤마 포맷 함수
				function formatNumber(number) {
					return number.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
				}

				// 총 가격 표시
				let total = 0;
				$("select").each(function () {
					const id = $(this).attr("id");
					total += parseInt($(this).val()) * prices[id];
				});
				$("#total2").text(formatNumber(total) + "원");
				$("#total1").text(sum);
			}
		});
	// 좌석 가격 및 결제 버튼 상태 업데이트 함수
	function countSeatPrice() {
		let sum = 0;

		// 선택한 인원 수 계산
		$("select").each(function () {
			sum += parseInt($(this).val());
		});

		const selectedSeatCount = $(".selectedSeat").length;
		$("#count").text(selectedSeatCount); // 선택된 좌석 수 업데이트

		if (selectedSeatCount == sum) {
			$("#gopay").css("background-color", "red"); // 결제 버튼 스타일 변경
			$("#gopay").prop("disabled", false); // 결제 버튼 활성화
		} else {
			$("#gopay").css("background-color", ""); // 결제 버튼 스타일 초기화
			$("#gopay").prop("disabled", true); // 결제 버튼 비활성화
		}
	}

	// 좌석 클릭 이벤트 핸들러
	$(".seatContainer").on("click", ".seat", function () {
		const selectedSeatCount = $(".selectedSeat").length;

		// 현재 클릭된 좌석의 선택 여부 확인
		const isSeatSelected = $(this).hasClass("selectedSeat");
		let sum = 0;

		$("select").each(function () {
			sum += parseInt($(this).val()); // 선택한 인원 수 계산
		});

		// 선택 가능한 좌석 수를 초과하면 경고 메시지 표시
		if (selectedSeatCount >= sum && !isSeatSelected) {
			alert(`최대 선택 가능한 좌석 수는 ${sum}개입니다.`);
			return;
		}

		// 좌석 선택 및 가격 업데이트
		$(this).toggleClass("selectedSeat");
		countSeatPrice();

		const seatId = $(this).data("seat");

		if (isSeatSelected) {
			// 좌석이 이미 선택되어 있었으면 선택 해제
			const seatIndex = selectedSeats.indexOf(seatId);
			if (seatIndex !== -1) {
				selectedSeats.splice(seatIndex, 1);
			}
		} else {
			// 좌석이 선택되지 않았었으면 선택
			selectedSeats.push(seatId);
		}
	});
});
