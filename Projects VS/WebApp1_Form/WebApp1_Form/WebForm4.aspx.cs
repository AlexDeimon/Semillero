using System;
using System.Collections.Generic;
using System.Text;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace WebApp1_Form
{
    public partial class WebForm4 : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void Button1_Click(object sender, EventArgs e)
        {
            StringBuilder sb = new StringBuilder();
            foreach (ListItem item in CheckBoxList1.Items)
            {
                if (item.Selected)
                {
                    sb.Append("</br>" + item);
                }
            }
            Label1.Text = "You have selected :" + sb.ToString();
        }
    }
}