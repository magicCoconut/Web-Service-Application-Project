<%@page import="java.text.ParseException"%>
<%@page import="java.util.TimeZone"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>JS Bin</title>

  <link href="bootstrap.min.css" rel="stylesheet" type="text/css">
  <link type="text/css" href="bootstrap-timepicker.min.css" />
  
</head>

    <%-- start web service invocation --%>
    <%
    try {
	org.me.cearch.SearchForClient_Service service = new org.me.cearch.SearchForClient_Service();
	org.me.cearch.SearchForClient port = service.getSearchForClientPort();
	port.runSever();
    } catch (Exception ex) {
    }
    %>


    <%-- DO THE SEARCH SERVICE --%>
    <%

    String data = "[]";

    int s = 1;
   
    try {
    	org.me.cearch.SearchForClient_Service service = new org.me.cearch.SearchForClient_Service();
    	org.me.cearch.SearchForClient port = service.getSearchForClientPort();
	 // TODO initialize WS operation arguments here
        if(request.getParameter("t1")!=null&&request.getParameter("t2")!=null){
            java.lang.String s1 = request.getParameter("t1");
            java.lang.String s2 = request.getParameter("t2");
            // TODO process result here
            java.util.List<java.lang.Integer> result = port.doSearch(s1, s2);
            //print result in console part
            System.out.println("-----------------Result -------------------- ");
            System.out.println(result.toString());
            
            //process the result date for line chart display
            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dateformat.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date dat = dateformat.parse(s1);
            long t = dat.getTime();
            data = "[";
            s = result.size();
            for(int i =0;i<s;i++){
               data = data +"["+ t+","+result.get(i)+"],";
               t = t+60000;
                   }
            data = data+"]";
          
        }
	
    } 
    catch (Exception ex) {
	// TODO handle custom exceptions here
    }
    %>


      
      <div style=" width: 360px; margin: 7% auto; margin-top: 20px; margin-bottom: 20px;">
      <!-- search window body  -->
        <p id="message">Search datetime ( time1-time2 ) to show result</p>

        <form action="" method="post" id ="goal">
          <div class="form-inline">
          <!-- time 1 input place -->
            <div class="form-group">
              <label for="exampleInputName2">January</label>
              <input type="number" class="form-control" id="exampleInputName1" placeholder="00" style=" width: 60px;" min="1" max="31">
            </div>
            <div class="form-group">
              <div class=" bootstrap-timepicker timepicker">
                <input id="timepicker1" type="text" class="form-control input-small">
            </div>
            </div>
          </div>
          <br>
          <!-- time 2 input place -->
          <div class="form-inline">
            <div class="form-group">
              <label for="exampleInputName2">January</label>
              <input type="number" class="form-control" id="exampleInputName2" placeholder="00" style=" width: 60px;"min="1" max="31">
            </div>
            <div class="form-group">
              <div class=" bootstrap-timepicker timepicker">
                <input id="timepicker2" type="text" class="form-control input-small">
            </div>
            </div>
          </div>

          </br>
          <div class="text-center">
          <!-- input button trigger the submit data -->
            <input name="updateButton" 
           type="button" 
           value="Submit" 
           onclick="getData()" />
          </div>
        </form>
      </div>

      <div id="graph-container" style=" width: 100%;
       text-align:center;">
        <!-- line chart dislay here -->
      <div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
      </div>



  <script src="Chart.min.js"></script>

  <script type="text/javascript" src="jQuery-2.1.3.min.js"></script>
  <script type="text/javascript" src="bootstrap.min.js"></script>
  <script type="text/javascript" src="bootstrap-timepicker.min.js"></script>
  <script src="highcharts.js"></script>
<script src="exporting.js"></script>
  

  <script type="text/javascript">

   // time force formate when user input
   $('#timepicker1').timepicker({
           showSeconds: true,
           showMeridian: false,
           minuteStep: 5,
            showInputs: false,
            disableFocus: true
        });
    // time force formate when user input
      $('#timepicker2').timepicker({
         showSeconds: true,
         showMeridian: false,
         minuteStep: 5,
          showInputs: false,
          disableFocus: true
      });

// js firstly check the input from user before sent to server
function getData(){

  var startdate = document.getElementById('exampleInputName1').value
  var starttime= document.getElementById('timepicker1').value
  var enddate = document.getElementById('exampleInputName2').value
  var endtime = document.getElementById('timepicker2').value

  function dateCompare(time1,time2) {
  var t1 = new Date();
  var parts = time1.split(":");
  t1.setHours(parts[0],parts[1],parts[2],0);
  var t2 = new Date();
  parts = time2.split(":");
  t2.setHours(parts[0],parts[1],parts[2],0);

  // returns 1 if greater, -1 if less and 0 if the same
  if (t1.getTime()>t2.getTime()) return 1;
  if (t1.getTime()<t2.getTime()) return -1;
  return 0;
  }
  var t = document.getElementById('message');

    if(startdate!=null && startdate!="" &&starttime!=null && starttime!="" &&enddate!=null && enddate!="" &&enddate!=null&&enddate!=""){
      if(startdate<=31&&enddate<=31){
        if(startdate<=enddate&&dateCompare(starttime,endtime)<=0){
          if(startdate<=9){
            startdate = "0"+startdate;
          }
          if(enddate<=9){
            enddate = "0"+enddate;
          }
            var t1 = "2015-01-"+startdate+" "+starttime;
            var t2= "2015-01-"+enddate+" "+endtime;
            // data sent to server
            document.getElementById("goal").action="index.jsp?t1="+t1+"&t2="+t2; 
            window.location.href = "index.jsp?t1="+t1+"&t2="+t2;
        } else{
          t.innerHTML = "Opps! Start time must less than end time";
        }
      } else{
          t.innerHTML = "Invalid date";
        }
    }else{
          t.innerHTML = "Flied can not be empty";
        }

}
// line chart draw
$(function () {
        // get result data from server to display line chart
        var data = <%=data%> ;

   
        $('#container').highcharts({
            chart: {
                zoomType: 'x'
            },
            title: {
                text: 'Number of pickups over time(in minute)'
            },
           subtitle: {
                text: document.ontouchstart === undefined ?
                        'Click and drag in the plot area to zoom in' : 'Pinch th to zoom in'
            },
            xAxis: {
                type: 'datetime'
            },
            yAxis: {
                title: {
                    text: 'Number of pickup'
                }
            },
            legend: {
                enabled: false
            },
            plotOptions: {
                area: {
                    fillColor: {
                        linearGradient: {
                            x1: 0,
                            y1: 0,
                            x2: 0,
                            y2: 1
                        },
                        stops: [
                            [0, Highcharts.getOptions().colors[0]],
                            [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
                        ]
                    },
                    marker: {
                        radius: 2
                    },
                    lineWidth: 1,
                    states: {
                        hover: {
                            lineWidth: 1
                        }
                    },
                    threshold: null
                }
            },

            series: [{
                type: 'area',
                name: 'Pickup number',
                data: data
            }]
        });
});

    </script>
</body>
</html>

