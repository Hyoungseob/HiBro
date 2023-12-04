let btt = document.gerElementById('back-to-top'),
    docElem = document.ducumentElement,
    offset, //스크롤을 올리고 남은 값 (아래로 삐져나온 양)
    scrollPos,// 스크롤의 양 (위로 삐져나온 양)
    docHeight,
    docHeight1;

    //**문서의 높이 계산하기*/
    docHeight = docElem.scrollHeight; //scrollHeight 문서 자체의 높이 측정
    console.log(docHeight);
    
    docHeight1 = docElem.offsetHeight;//scrollHeight 문서 자체의 높이 측정 보통 
    console.log(docHeight);                                  //scrollHeight와 offsetHeight가 같은 값을 지니지만 브라우저에 따라 다를 수 있다.
                                      //여기서 두 값중 더 큰 값을 가져와서 쓰겠다.
    docHeight = Math.max(docElem.offsetHeight,docElem.scrollHeight);
    // 두 값중 큰값을 docHeight로 선언하겠다.(Math.min(a,b);는 작은 값을 취하는 함수)
    if(docHeight !=0 ){
        offset = docHeight/4;
    }//0, 'undefined' , ' ' 같이 사용가능 

    //**스크롤 이벤트 추가*/
    window.addEventListener('scroll',function (){
        scrollPos = docElem.scrollTop; //scrollTop 스크롤 가능한 컨테이너 요소의 상단 가장자리부터
        console.log(scrollPos);        //현재 스크롤된 위치까지의 거리를 반환
        
        //btt.className = (scrollPos > offset) ? 'visible':''; 아래 if문을 축약한 형태
        // 축약 과정 1.{}사라짐, 2.if 대신 ? 3.else대신 ;을 :으로 바꿔줌. 4.btt.className이 앞으로 이동(결과값)
        if(scrollPos > offset){
            btt.className = 'visible'; //css에 back-to-top.visible 설정 따로 하기
        }else{
            btt.className = '';
        }
    });                               
        //btt.className = 'test' btt변수로 선택한 자리의 class를 test로 바꿈
        //클래스 명 추가는 document.classList.add('newClassName');

    btt.addEventListener('click',function(ev){
        ev.preventDefault(); // 링크의 본연의 기능을 막는다.(btt가 a태그로 링크되어있음)
        //docElem.scrollTop = 0;
        scrollToTop();
    });
    function scrollToTop(){
        //일정 시간마다 할일
        //var scrollInterval = setInterval(할일,시간);
        // 0.0015s = 15
        //할일 = function(){실제로 할일}
        //윈도우 스크롤이 0이 아닐 때
        //실제로 할일 window.scrollBy(x,y); 윈도우의 스크롤 양을 조금씩 뺀다.
        //스크롤이 0이면 setInterval을 멈춰라. (clearInterval(이름));
        var scrollInterval = setInterval(function(){
            if(scrollPos != 0){
                window.scrollBy(0,-55);
            }else{
                clearInterval(scrollInterval);
            }
        },15);
    }