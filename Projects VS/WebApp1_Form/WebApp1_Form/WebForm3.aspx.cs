using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace WebApp1_Form
{
    public partial class WebForm3 : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void Button1_Click(object sender, EventArgs e)
        {
            Label1.Text = "You have selected </br> Item=" +
                              RadioButtonList1.SelectedItem.Text + "</br> Value =" +
                              RadioButtonList1.SelectedValue + "</br> Index =" +
                              RadioButtonList1.SelectedIndex;
        }
        protected void Button2_Click(object sender, EventArgs e)
        {
            if (RadioButtonList1.RepeatDirection == RepeatDirection.Vertical)
            {
                RadioButtonList1.RepeatDirection = RepeatDirection.Horizontal;
            }
            else
            {
                RadioButtonList1.RepeatDirection = RepeatDirection.Vertical;
            }
        }
    }
}
