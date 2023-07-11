<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="WebForm3.aspx.cs" Inherits="WebApp1_Form.WebForm3" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
        <asp:RadioButtonList ID="RadioButtonList1" runat="server">
              <asp:ListItem Value="1">Red</asp:ListItem>
              <asp:ListItem Value="2">Green</asp:ListItem>
              <asp:ListItem Value="3">Blue</asp:ListItem>
              <asp:ListItem Value="4">Yellow</asp:ListItem>
              <asp:ListItem Value="5">Orange</asp:ListItem>
        </asp:RadioButtonList>
        <asp:Button ID="Button1" runat="server" Text="Show" OnClick="Button1_Click" />
        <asp:Button ID="Button2" runat="server" Text="Repeat Direction" /><br />
        <asp:Label ID="Label1" runat="server" Text="Label"></asp:Label>
    </form>

</body>
</html>
