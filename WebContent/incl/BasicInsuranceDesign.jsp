<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	
  	<h3> 보험 기본 정보 입력  </h3>
  	
  		<br>

        	<table>
          		<tr>
            		<td> 보험 이름 : </td>
            		<td> <input type="text" name="insuranceName" size = "30"> </td>
          		</tr>
          		<tr>
            		<td> 보험료 : </td>
            		<td> <input type="text" name="insuranceFee" size = "30"> </td>
          		</tr>
          		<tr>
            		<td style="vertical-align:top"> 보험 설명서  :  </td>
            		<td><textarea name="insuranceManual" cols="70" rows="5" placeholder="보험에 대한 설명을 작성하여 주십시오."></textarea></td>
          		</tr>
      	  		<tr>
            		<td style="vertical-align:top"> 판매 메뉴얼  :  </td>
            		<td><textarea name="insuranceSalesManual" cols="70" rows="5" placeholder="이 보험의 판매  tip을 작성하여 주십시오."></textarea></td>
          		</tr>
        	</table>