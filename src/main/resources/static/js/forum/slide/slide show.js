//변수 지정
var sliderWrapper = document.getElementsByClassName('container'),//클래스명 container
    sliderContainer = document.getElementsByClassName('slider-container'),
    slides = document.getElementsByClassName('slide'), //클래스명 slide
    slideCount = slides.length, //슬라이드의 개수
    currentIndex = 0, //사용자가 보는 페이지의 인덱스 첫 화면 0으로 설정
    topheight = 0, //슬라이드 높이중 가장 큰 값
    navPrev = document.getElementById('prev'), //아이디 prev
    navNext = document.getElementById('next'); //아이디 next

//슬라이드의 높이 확인하여 부모의 높이로 지정하기
    //요소의 높이를 구해올 때 offsetHeight사용
    //topHeight = slides.offsetHeight; 배열로 저장되어 있으므로 인덱스 번호 입력
    //topHeight = slides[0].offsetHeight;
function calculateTallestSlide(){
    /* fot (시작; 끝값(조건); 증감){
        실제로 반복할 일
    } */
    for(var i=0; i<slideCount; i++){ //++를 후위로 주는것에 유의!
        if(slides[i].offsetHeight > topHeight){
            topHeight = slides[i].offsetHeight;
        }
        slides[i].offsetHeight;
    }
    sliderWrapper[0].style.height = topHeight + 'px';
    sliderContainer[0].style.height = topHeight + 'px';
};
calculateTallestSlide();

//슬라이드가 있으면 가로로 배열하기 (flex를 쓰면 생략해도 되는 과정이다.)
/**for(시작; 조건; 증감){실제로 반복할 일
 *    slides[0].style.left = ??? + '%'} 컨텐츠의 가로길이가 동일하므로 퍼센트로 표현*/
for(var i=0; i<slideCount; i++){
    slides[i].style.left = i*100 + '%'; //컨텐츠 크기가 동일하므로
}
//슬라이드 이동 함수
function goToSlide(idx){
    sliderContainer[0].style.left = idx * -100 + '%';
    sliderContainer[0].classList.add('animated');
    //기존에 class가 설정되어있지 않으면 .className 하면된다(있으면 기존 class값이 다사라짐.)
    currentIndex = idx;
    
    updateNav();
}
function updateNav(){
    //처음일때
    if(currentIndex == 0){
        navPrev.classList.add('disabled');
    }else{
        navPrev.classList.remove('disabled'); //remove는 제거
    }
    //마지막일때
    if(currentIndex == slideCount-1){
        navNext.classList.add('disabled');
    }else{
        navNext.classList.remove('disabled');
    }
}

//버튼기능 업데이트 함수

//버튼을 클릭하면 슬라이드 이동시키기
navPrev.addEventListener('click',function(event){
    event.preventDefault();
    goToSlide(currentIndex - 1);
})
navNext.addEventListener('click',function(event){
    event.preventDefault();
    goToSlide(currentIndex + 1);
})

//첫번째 슬라이드 먼저 보이도록 하기
goToSlide(0); //이게 없으면 웹사이트 첫화면에서 navPrev버튼이 존재함