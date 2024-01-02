$(document).ready(function () {
	let selectedLi = null; // 현재 선택된 li 요소
	let theaterNameClicked = false; // '.theaterName' 클릭 여부
	let movieClicked = false; // '.movieClicked' 클릭 여부
	let dateClicked = false;
	let dateTimeClicked = false;

	// movieTitle 클릭 이벤트 처리
	$(".movieTitle").click(function () {
		const $this = $(this);

		if (selectedLi && selectedLi !== this) {
			dateTimeClicked = false;
		}
		if (selectedLi !== this) {
			$this.addClass("movieTitleClicked");
			$this.siblings().removeClass("movieTitleClicked");
			movieClicked = true; // '.movieClicked' 클릭 여부 업데이트
		}
		displayDateTime();
	});

	// theater의 직계 자손인 ul의 직계 자손인 li 클릭 이벤트 처리
	$(".theaterLocation").click(function () {
		const $this = $(this);
		const $subMenu = $this.find(".theaterList");

		if (selectedLi && selectedLi !== this) {
			$(selectedLi).css("background-color", "").find(".theaterList").css("display", "none");
			$(".theaterName").removeClass("theaterNameClicked");
			theaterNameClicked = false;
			dateTimeClicked = false;
			$("#date_time").css("display", "none"); // #date_time 숨김
		}

		if (selectedLi === this) {
			// 이미 선택된 li를 다시 클릭한 경우, 선택 유지
			$subMenu.css("display", "block");
		} else {
			$subMenu.css("display", "block");
			$this.css("background-color", "#ccc");
			dateTimeClicked = false;
			selectedLi = this; // 현재 선택된 li 업데이트
		}

		displayDateTime();
	});

	// .theaterName 클릭하면 배경색 변경
	$(".theaterName").click(function () {
		const $this = $(this);
		$this.addClass("theaterNameClicked");
		$this.siblings().removeClass("theaterNameClicked");
		theaterNameClicked = true;

		displayDateTime();
	});

	// .date 클릭이벤트
	$(".date").click(function (){
		const $this = $(this);
		if (selectedLi && selectedLi !== this) {
			dateTimeClicked = false;
		}
		if (selectedLi !== this) {
			$this.addClass("dateClicked");
			$this.siblings().removeClass("dateClicked");
			dateClicked = true;
		}
		displayDateTime();
	});

	// movie과 .theaterName 둘 다 클릭되었을 때에만 #date_time 표시
	function displayDateTime() {
		if (movieClicked && theaterNameClicked && dateClicked) {
			getScreenDate();
			$("#date_time").css("display", "block"); // #date_time 표시
		} else {
			$("#screenDateList li").removeClass("dateTimeClicked"); // 클릭한 li에 클래스 추가 및 배경색 변경
			dateTimeLiClicked = false;
		}
		displaySeatSelect();
	}

	function getScreenDate() {
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");

		var movieCode = $(".movieTitleClicked ").val();
		var theaterCode = $(".theaterNameClicked").val();
		var selectedDate = $(".dateClicked").val();

		var url = "/ticketing";
		$.ajax({
			url: url,
			type: "POST",
			beforeSend: function (xhr) {
				xhr.setRequestHeader(header, token);
			},
			data: {
				movieCode: movieCode,
				theaterCode: theaterCode,
				selectedDate: selectedDate
			},
			success: function (result, status) {
				// 받아온 데이터를 화면에 렌더링합니다.
				var ulElement = $("#screenDateList");

				// 기존의 자식 요소들을 모두 제거
				ulElement.empty();


				var displayedLocations = []; // 이미 출력한 위치를 기록할 배열
				// 받아온 데이터를 ulElement에 추가
				result.forEach(function (screenDate) {
					var date = moment(screenDate.screeningDateTime);

                        // 날짜 포맷팅
                    var formattedDate = date.format('HH:mm');

                    // 스크린 위치가 이미 출력되었는지 확인
                    if (!displayedLocations.includes(screenDate.screen.location)) {
                        var screenLocation = $("<span>").html(screenDate.screen.location);
                        displayedLocations.push(screenDate.screen.location); // 출력한 위치를 기록
                        ulElement.append(screenLocation);
						var spanElement = $("<span>").html(screenDate.screen.type);
						ulElement.append(spanElement);
                    }

                    var liElement = $("<li>").attr("data-date", screenDate.screeningDateTime).html(formattedDate);

                    ulElement.append(liElement);
				});
			},
			error: function (jqXHR, status, error) {
				if (jqXHR == "401") {
					alert("로그인 하세요");
				} else {
					alert(jqXHR.responseJSON.message);
				}
			},
		});
	}
	
	// #screenDateList의 ul li 클릭하면 다른 div의 배경색 변경
	$("#screenDateList").on("click", "li", function () {
		$(this).siblings().removeClass("dateTimeClicked"); // 다른 li 초기화
		$(this).addClass("dateTimeClicked"); // 클릭한 li에 클래스 추가 및 배경색 변경
        dateTimeClicked = true;
		displaySeatSelect();
	});

	function displaySeatSelect() {
		if (dateTimeClicked) {
			$("#goseatsel").css("background-color", "red");
		} else {
			$("#goseatsel").css("background-color", "#ccc");
		}
	}

	$("#goseatsel").click(function () {
		if ($("#goseatsel").css("background-color") === "rgb(255, 0, 0)") {
			$("#goseatsel").css("display", "none");
			$("#step1").css("display", "none");
			$("#step2").css("display", "block");
			$("#backmovsel").css("display", "block");
			$("#gopay").css("display", "block");
			$("#step3").css("display", "none");

			var token = $("meta[name='_csrf']").attr("content");
			var header = $("meta[name='_csrf_header']").attr("content");

			var screenDateTime = $(".dateTimeClicked").data("date");

			var url = "/seat";
			$.ajax({
				url: url,
				type: "POST",
				beforeSend: function (xhr) {
					xhr.setRequestHeader(header, token);
				},
				data: {
					localDateTime: screenDateTime,
				},
				success: function(result, status) {
                    $("#screenDateTime").html(screenDateTime);
                },
				error: function (jqXHR, status, error) {
					if (jqXHR == "401") {
						alert("로그인 하세요");
					} else {
						alert("아무튼오류임");
					}
				},
			});
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
