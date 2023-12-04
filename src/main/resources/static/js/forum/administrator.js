document.addEventListener("DOMContentLoaded", function () {
	const refreshify = document.querySelector(".logo"),
		userStateSelect = document.querySelector(".selectUserState"),
		userManagement = document.getElementById("membershipUser"),
		poinManagement = document.getElementById("membershipPoin"),
		inquiryManagement = document.getElementById("membershipInquiry");

	const userManagementContent = document.querySelector(".noticeBoard"),
		poinManagementContent = document.querySelector(".noticeBoard_point"),
		inquiryManagementContent = document.querySelector(".noticeBoard_Inquiry_History");

	function sidebarSelectBtn(user, point, inquiry) {
		userManagementContent.style.display = user;
		poinManagementContent.style.display = point;
		inquiryManagementContent.style.display = inquiry;
	}

	function SelectResults() {
		const selectedValue = userStateSelect.value;

		if (selectedValue == "user") {
			sidebarSelectBtn("block", "none", "none");
		} else if (selectedValue == "poinManagement") {
			sidebarSelectBtn("none", "block", "none");
		} else if (selectedValue == "InquiryHistory") {
			sidebarSelectBtn("none", "none", "block");
		}
	}

	refreshify.addEventListener("click", function () {
		sidebarSelectBtn("block", "none", "none");
		userStateSelect.value = "user"; //selectedValue를 쓰면 안되는 이유 기억
	});

	userManagement.addEventListener("click", function () {
		sidebarSelectBtn("block", "none", "none");
		userStateSelect.value = "user";
	});

	poinManagement.addEventListener("click", function () {
		sidebarSelectBtn("none", "block", "none");
		userStateSelect.value = "poinManagement";
	});

	inquiryManagement.addEventListener("click", function () {
		sidebarSelectBtn("none", "none", "block");
		userStateSelect.value = "InquiryHistory";
	});

	userStateSelect.addEventListener("change", function () {
		SelectResults();
	});

	SelectResults();
});
