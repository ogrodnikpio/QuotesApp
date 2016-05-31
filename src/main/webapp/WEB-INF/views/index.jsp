<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>

	<head>
		<title>Quotes Application</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel="stylesheet" type="text/css" href="<c:url value="/static/resources/css/screenStyle.css"/>"/>
		<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
		<script type="text/javascript">
			<c:choose>
			<c:when test="${fn:length(quotes) != 0}">
			google.charts.load('current', {'packages':['corechart','table']});
			google.charts.setOnLoadCallback(drawChart);
			google.charts.setOnLoadCallback(drawTable);
			function drawChart() {
				var data = google.visualization.arrayToDataTable([
					['Date','Value'],
					<c:forEach items="${quotes}" var="entry">
					[ new Date(${entry.quoteDate}), ${entry.quoteValue} ],
					</c:forEach>
				]);
				var options = {
					title: 'Chart performance of found',
					legend: { position: 'bottom' },
					vAxis: {format: 'decimal'}
				};
				var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));
				chart.draw(data, options);
			}
			function drawTable() {
				var data = new google.visualization.DataTable();
				data.addColumn('date', 'Quote Date');
				data.addColumn('number', 'Quote Value');
				data.addRows([
					<c:forEach items="${quotes}" var="entry">
					[ new Date(${entry.quoteDate}), ${entry.quoteValue} ],
					</c:forEach>
				]);
				var options = {
					width: '100%',
					height: '100%',
					page: 'enable',
					pageSize: 18

				}
				var tableDraw = new google.visualization.Table(document.getElementById('table_div'));
				tableDraw.draw(data, options);
			}
			</c:when>
			</c:choose>


		</script>
	</head>
	<body>
		<div id="container">
			<div id="content">
				<h1>Welcome to Quotes Application</h1>
				<form:form commandName="datesWrapper" id="reg">
					<h2>Insert dates</h2>
					<table>
						<tbody>
							<tr>
								<td><form:label path="date">First date:</form:label></td>
								<td><form:input path="date"/></td>
								<td><form:errors class="invalid" path="date"/></td>
							</tr>
							<tr>
								<td><form:label path="date2">Second date:</form:label></td>
								<td><form:input path="date2"/></td>
								<td><form:errors class="invalid" path="date2"/></td>
							</tr>
						</tbody>
					</table>
					<table>
						<tr>
							<td>
								<input type="submit" value="Submit" class="register"/>
							</td>
						</tr>
					</table>
					<p>Dates must be in format dd-mm-yyyy</p>
				</form:form>
			<div id="table_wrapper">
				<div id="table_div"></div>
			</div>
			<div id="curve_chart"></div>
		</div>
	</body>
</html>
