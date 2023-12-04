/**
 * 
 */

 function hideMiddleCharacters(str) {
        if (str.length <= 2) {
            return str;
        }
        const firstChar = str.charAt(0);
        const lastChar = str.charAt(str.length - 1);
        const middleChars = '*'.repeat(str.length - 2);
        return firstChar + middleChars + lastChar;
    }

    function isOnlyKoreanOrEnglish(str) {
        const regex = /^[a-zA-Z가-힣\s]+$/;
        return regex.test(str);
    }

    function isOnlyNumbers(str) {
        const regex = /^[0-9]+$/;
        return regex.test(str);
    }

    function submitComment() {
        const name = document.getElementById("name").value;
        const phoneNumber = document.getElementById("phoneNumber").value;
        const commentText = document.getElementById("commentText").value;

        if (!isOnlyKoreanOrEnglish(name)) {
            alert("이름은 한글 또는 영어만 입력할 수 있습니다.");
            return;
        }

        if (!isOnlyNumbers(phoneNumber)) {
            alert("전화번호는 숫자만 입력할 수 있습니다.");
            return;
        }

        if (name.trim() === "" || phoneNumber.trim() === "" || commentText.trim() === "") {
            alert("이름, 전화번호, 댓글을 모두 입력해주세요.");
            return;
        }

        const hiddenName = hideMiddleCharacters(name);
        const hiddenPhoneNumber = hideMiddleCharacters(phoneNumber);

        const commentList = document.getElementById("commentList");
        const commentItem = document.createElement("li");
        commentItem.className = "comment-item";
        commentItem.innerHTML = `<p><strong>${hiddenName}</strong> (${hiddenPhoneNumber})</p><p>${commentText}</p>`;
        commentList.appendChild(commentItem);

        // 서버로 댓글 및 정보 전송 및 저장하는 로직 추가
        // 여기서는 모의 데이터를 사용하여 구현합니다.
    }