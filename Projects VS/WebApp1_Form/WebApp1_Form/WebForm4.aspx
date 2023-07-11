<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="WebForm4.aspx.cs" Inherits="WebApp1_Form.WebForm4" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
        <asp:CheckBoxList ID="CheckBoxList1" runat="server">
            <asp:ListItem Value="1">HTML</asp:ListItem>
              <asp:ListItem Value="2">CSS</asp:ListItem>
              <asp:ListItem Value="3">JavaScript</asp:ListItem>
              <asp:ListItem Value="4">Bootstrap</asp:ListItem>
              <asp:ListItem Value="5">TypeScript</asp:ListItem>
        </asp:CheckBoxList>
        <asp:Button ID="Button1" runat="server" Text="Show" OnClick="Button1_Click" />
        <asp:Button ID="Button2" runat="server" Text="Repeat Direction" /><br />
        <asp:Label ID="Label1" runat="server" Text="Label"></asp:Label>
    </form>
</body>
</html>
