<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>InsuranceSystem</title>
</head>
<body>
	<jsp:include page="/incl/staticHeader.jsp" />
	<jsp:include page="/incl/Header.jsp" />
	
	    <section class="site-section">
      <div class="container">
        <div class="row">
          <div class="col-md-6 col-lg-4">
            <div class="p-3 box-with-humber">
              <div class="number-behind">01.</div>
              <h2 class="text-primary">�μ���ħ�� ����</h2>
              <p class="mb-4">�� ���躰�� �����򰡿� ����ʰ� �ۼ����ִ� ��ħ���� �μ� ������ �� �� �����Ѵ�.</p>
              <ul class="list-unstyled ul-check primary">
                <li>ȭ�纸�� �μ���ħ��</li>
                <li>�ڵ������� �μ���ħ��</li>
                <li>�Ǻ��� �ν���ħ��</li>
              </ul>
              <p><a href="ShowAcceptanceGuide.jsp">�μ���ħ�� ����</a></p>
            </div>
          </div>

          <div class="col-md-6 col-lg-4">
            <div class="p-3 box-with-humber">
              <div class="number-behind">02.</div>
              <h2 class="text-primary">�μ���ħ�� ����</h2>
              <p class="mb-4">���� ������ ������ �����򰡳� ����� ������ ����ʸ� �ۼ��Ͽ� ��ħ���� ����� �ִ�.</p>
              <ul class="list-unstyled ul-check primary">
                <li>ȭ�纸�� �μ���ħ��</li>
                <li>�ڵ������� �μ���ħ��</li>
                <li>�Ǻ��� �ν���ħ��</li>
              </ul>
			  <p><a href="AcceptanceGuideDesign.jsp">�μ���ħ�� ����</a></p>
            </div>
          </div>
        </div>
      </div>
    </section>

	<jsp:include page="/incl/Footer.jsp" />
</body>
</html>