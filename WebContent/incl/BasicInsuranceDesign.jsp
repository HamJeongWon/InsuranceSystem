<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	
  	<h3> ���� �⺻ ���� �Է�  </h3>
  	
  		<br>

        	<table>
          		<tr>
            		<td> ���� �̸� : </td>
            		<td> <input type="text" name="insuranceName" size = "30"> </td>
          		</tr>
          		<tr>
            		<td> ����� : </td>
            		<td> <input type="text" name="insuranceFee" size = "30"> </td>
          		</tr>
          		<tr>
            		<td style="vertical-align:top"> ���� ������  :  </td>
            		<td><textarea name="insuranceManual" cols="70" rows="5" placeholder="���迡 ���� ������ �ۼ��Ͽ� �ֽʽÿ�."></textarea></td>
          		</tr>
      	  		<tr>
            		<td style="vertical-align:top"> �Ǹ� �޴���  :  </td>
            		<td><textarea name="insuranceSalesManual" cols="70" rows="5" placeholder="�� ������ �Ǹ�  tip�� �ۼ��Ͽ� �ֽʽÿ�."></textarea></td>
          		</tr>
        	</table>