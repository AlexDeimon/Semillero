<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="WebForm2.aspx.cs" Inherits="WebApp1_Form.WebForm2" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
<style type="text/css">
    .auto-style1 { width: 103px; }
    .auto-style3 { width: 165px; }
    .auto-style4 { width: 143px; }
</style>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
        <table style="width: 76%;">
            <tr>
                <td class="auto-style1">    
        <asp:Label ID="Label1" runat="server" Text="Label">Name</asp:Label>
                </td>
                <td class="auto-style4">
        <asp:TextBox ID="txtName" runat="server"></asp:TextBox>
                </td>
                <td class="auto-style3"> </td>
            </tr>
            <tr>
                <td class="auto-style1">
        <asp:Label ID="Label2" runat="server" Text="Label">Password</asp:Label>
                </td>
                <td class="auto-style4">
        <asp:TextBox ID="txtPassword" runat="server" TextMode="Password"> </asp:TextBox>
                </td>
                <td class="auto-style3"> </td>
            </tr>
            <tr>
                <td class="auto-style1">    
        <asp:Label ID="Label3" runat="server" Text="Label">EmailID</asp:Label>
                </td>
                <td class="auto-style4">
        <asp:TextBox ID="txtEmailID" runat="server"></asp:TextBox>
                </td>
                <td class="auto-style3"> </td>
            </tr>
            <tr>
                <td class="auto-style1">    
        <asp:Label ID="Label5" runat="server" Text="Label">Gender</asp:Label>
                </td>
                <td class="auto-style4">
                    <asp:RadioButton ID="RDBmale" runat="server" text="Male"/> <br />
                    <asp:RadioButton ID="RDBfemale" runat="server" text="Female"/>
                </td>
                <td class="auto-style3"> </td>
            </tr>
            <tr>
                <td class="auto-style1">    
        <asp:Label ID="Label6" runat="server" Text="Label">Prefered Cities</asp:Label>
                </td>
                <td class="auto-style4">
                    <asp:CheckBox ID="chbDelhi" runat="server" text="Delhi"/><br />
                    <asp:CheckBox ID="chbAgra" runat="server" text="Agra"/><br />
                    <asp:CheckBox ID="chbPune" runat="server" text="Pune"/><br />
                </td>
                <td class="auto-style3"> </td>
            </tr>
            <tr>
                <td class="auto-style1">
        <asp:Label ID="Label7" runat="server" Text="Label">Current Address</asp:Label>
                </td>
                <td class="auto-style4">
        <asp:TextBox ID="txtAddress" runat="server" Height="54px" TextMode="MultiLine" Width="211px"></asp:TextBox>
                </td>
                <td class="auto-style3"> </td>
            </tr>
            <tr>
                <td class="auto-style1">
        <asp:Button ID="btnSubmit" runat="server" Text="Submit" OnClick="btnSubmit_Click" />
                </td>
                <td class="auto-style4">
        <asp:Button ID="btnCancel" runat="server" Text="Cancel" />
                </td>
                <td class="auto-style3"> </td>
            </tr>
        </table> 
        <asp:TextBox ID="txtResult" runat="server" Height="100px" TextMode="MultiLine" Width="400px"></asp:TextBox>
    </form>
</body>
</html>
