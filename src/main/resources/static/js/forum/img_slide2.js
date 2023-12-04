let cutscenes = document.getElementsByClassName('slides'),
    cutscene = document.getElementsByClassName('video_img'),
    prevBtn = document.getElementsByClassName('prev'),
    nextBtn = document.getElementsByClassName('next'),
    slide_container = document.getElementsByClassName('slide-wrapper'),
    currentIdx = 0,
    container_width = 0,
    cutscene_Count = cutscenes.length;

   //슬라이드의 가로 길이를 확인하여 부모의 가로길이로 지정하기
function calculateWidth(){
    for(var i=0; i<cutscene_Count; i++){
        container_width = cutscene[i].offsetWidth 
    }
    slide_container.width = container_width + 'px';
}
calculateWidth();
   //슬라이드 가로로 배열하기 (flex 사용해서 생략가능)

   //슬라이드 이동함수
function moveSlide(idx){
    cutscenes.width = -container_width + 'px';
}
   //버튼기능 업데이트 함수
prevBtn.addEventListener('click',function(){
    if(currentIdx > 0){
        moveSlide();
    }else{
        //버튼을 지운다.
    }
})
nextBtn.addEventListener('click',function(){
    if(currentIdx < cutscene_Coun-1){
        moveSlide();
    }else{
        //버튼을 지운다.
    }
})
   //버튼을 클릭하면 슬라이드 이동시키기