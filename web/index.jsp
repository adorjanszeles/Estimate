<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>PEstimate Application</title>
  </head>
  <body>

  <h1>Test Page for Create Persons</h1>

  <h2>Create Person</h2>
  <form action="CreatePersonServlet">
    <table>
      <tr>
        <td>Name:</td>
        <td><input type="text" name="name" /></td>
      </tr>
      <tr>
        <td>E-mail:</td>
        <td><input type="text" name="email" /></td>
      </tr>
      <tr>
        <td>Nick name:</td>
        <td><input type="text" name="nickName" /></td>
      </tr>
      <tr>
        <td>Password:</td>
        <td><input type="text" name="password" /></td>
      </tr>

      <tr>
        <td colspan="2"><input type="submit" value="OK" /></td>
      </tr>
    </table>
  </form>
  ${resultOfPersonCreation}

  <h2>Get Person</h2>
  <form action="QueryPersonServlet">
    <table>
      <tr>
        <td colspan="2"><input type="submit" value="Query" /></td>
      </tr>
    </table>
  </form>
  ${resultOfPersonQuery}

  </body>
</html>
