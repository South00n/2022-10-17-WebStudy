<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
<div class="wrapper row3">
  <div id="breadcrumb" class="clear"> 
    <ul>
      <li><a href="#">Home</a></li>
      <li><a href="#">Lorem</a></li>
      <li><a href="#">Ipsum</a></li>
      <li><a href="#">Dolor</a></li>
    </ul>
  </div>
</div>

<div class="wrapper row3">
  <main class="container clear"> 
    <!-- main body --> 
    <table class="table">
      <tr>
        <c:forTokens items="${vo.poster }" delims="^" var="image">
          <td>
            <img src="${image }" style="width: 100%" class="img-rounded">
          </td>
        </c:forTokens>
      </tr>
    </table>
    <div class="content one_half first"> 
      <div>
        <%-- 상세 --%>
        <table class="table">
          <tr>
            <td colspan="2"><h4>${vo.name }&nbsp;<span style="color:orange">${vo.score }</span></h4></td>
          </tr>
          <tr>
            <th width="20%" style="color:lightgray">주소</th>
            <td width="80%">${addr1 }<br>
              <sub style="color:gray">지번:${addr2 }</sub>
            </td>
          </tr>
          <tr>
            <th width="20%" style="color:lightgray">전화번호</th>
            <td width="80%">${vo.tel }</td>
          </tr>
          <tr>
            <th width="20%" style="color:lightgray">음식종류</th>
            <td width="80%">${vo.type }</td>
          </tr>
          <tr>
            <th width="20%" style="color:lightgray">가격대</th>
            <td width="80%">${vo.price }</td>
          </tr>
          <tr>
            <th width="20%" style="color:lightgray">주차</th>
            <td width="80%">${vo.parking }</td>
          </tr>
          <tr>
            <th width="20%" style="color:lightgray">영업시간</th>
            <td width="80%">${vo.time }</td>
          </tr>
          <c:if test="${vo.menu != 'no' }">
          <tr>
            <th width="20%" style="color:lightgray">메뉴</th>
            <td width="80%">
              <ul style="padding-left: 5px">
                <c:forTokens items="${vo.menu }" delims="원" var="m">
                  <li style="list-style:none">${m }원</li>
                </c:forTokens>
              </ul>
            </td>
          </tr>
          </c:if>
          <tr>
            <td colspan="2" class="text-right">
              <a href="#" class="btn btn-xs btn-info">좋아요(0)</a>
              <a href="#" class="btn btn-xs btn-success">찜하기</a>
              <a href="#" class="btn btn-xs btn-warning">예약하기</a>
              <a href="javascript:history.back()" class="btn btn-xs btn-primary">목록</a>
            </td>
          </tr>
        </table>
      </div>
      <div id="comments">
        <%-- 댓글 --%>
      </div>
    </div>
    
    <div class="sidebar one_half"> 
      <div class="sdb_holder">
        <div id="map" style="width:100%;height:350px;"></div>

		<script>
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		    mapOption = {
		        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
		        level: 3 // 지도의 확대 레벨
		    };  
		
		// 지도를 생성합니다    
		var map = new kakao.maps.Map(mapContainer, mapOption); 
		
		// 주소-좌표 변환 객체를 생성합니다
		var geocoder = new kakao.maps.services.Geocoder();
		
		// 주소로 좌표를 검색합니다
		geocoder.addressSearch('${addr1 }', function(result, status) {
		
		    // 정상적으로 검색이 완료됐으면 
		     if (status === kakao.maps.services.Status.OK) {
		
		        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
		
		        // 결과값으로 받은 위치를 마커로 표시합니다
		        var marker = new kakao.maps.Marker({
		            map: map,
		            position: coords
		        });
		
		        // 인포윈도우로 장소에 대한 설명을 표시합니다
		        var infowindow = new kakao.maps.InfoWindow({
		            content: '<div style="width:150px;text-align:center;padding:6px 0;">${vo.name }</div>'
		        });
		        infowindow.open(map, marker);
		
		        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
		        map.setCenter(coords);
		    } 
		});    
		</script>
      </div>
      <div class="sdb_holder">
        <%-- 인근 명소 ... --%>
      </div>
    </div>
    
    <!-- / main body -->
    <div class="clear"></div>
  </main>
</div>
</body>
</html>
