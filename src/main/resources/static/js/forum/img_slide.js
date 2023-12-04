
var slides = document.querySelector('.slides');
var	slide = document.querySelectorAll('.video_img');
var currentIdx = 0;
var slideCount = slide.length;
var slideWidth = 600;
var prevBtn = document.querySelector('.prev');
var nextBtn = document.querySelector('.next');

/** 픽셀값 넣어주는 것 잊지말것 */
/*slides.style.width = (slideMargin+slideWidth)*slideCount - slideMargin +'px';*/

function moveSlide(num) {
	
	slides.style.left= -num*600+'px';
	currentIdx = num;
	/**이제 보고있는 이미지의 번호 */
}

nextBtn.addEventListener('click', function(){
	if(currentIdx<slideCount-1){
		moveSlide(currentIdx+1);
	}else{
		moveSlide(slideCount-1);
	}
});
/* 목표: moverSlide(9)를 이미지를 넣는만큼 자동으로 함수에 들어갈 숫자 계산*/

prevBtn.addEventListener('click', function(){
	if(currentIdx>0){
		moveSlide(currentIdx-1);
	}else{
		moveSlide(0);
	}
});




