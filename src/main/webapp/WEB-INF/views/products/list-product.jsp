<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Listagem dos Livros</title>
</head>
<body>
${sucesso}
<table>
	<tr>
		<td>Título</td>
		<td>Valores</td>
	</tr>
	
	<c:forEach items="${products}" var="product">
		<tr>
			<td>
				<a href="${spring:mvcUrl('PC#show').arg(0, product.id).build()}">
					${product.title}
				</a>
			</td>
			
			<td>
				<c:forEach items="${product.prices}" var="price">
					[${price.value} - ${price.bookType}]
				</c:forEach>
			</td>
		</tr>
	</c:forEach>
	
</table>

</body>
</html>