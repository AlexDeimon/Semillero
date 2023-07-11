using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace WebApp1_Form
{
    public partial class WebForm2 : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void btnSubmit_Click(object sender, EventArgs e)
        {
            string Name = "Name:  " + txtName.Text;
            string Email = "Email: " + txtEmailID.Text;
            string Gender = "";
            if (RDBmale.Checked)
            {
                Gender = "Gender: " + RDBmale.Text;
            }
            if (RDBfemale.Checked)
            {
                Gender = "Gender: " + RDBfemale.Text;
            }
            string Cities = "";
            if (chbAgra.Checked)
            {
                Cities += chbAgra.Text + "\n";
            }
            if (chbDelhi.Checked)
            {
                Cities += chbDelhi.Text + "\n";
            }
            if (chbPune.Checked)
            {
                Cities += chbPune.Text + "\n";
            }
            string Address = "Address:" + txtAddress.Text;
            txtResult.Text = Name + "\n" + Email + "\n" + Gender + "\n" + "City: " + Cities + "\n" + Address;
        }
        protected void btnCancel_Click(object sender, EventArgs e)
        {
            txtName.Text = String.Empty;
            txtPassword.Text = String.Empty;
            txtAddress.Text = String.Empty;
            txtEmailID.Text = String.Empty;
            RDBfemale.Checked = false;
            RDBmale.Checked = false;
            chbPune.Checked = false;
            chbAgra.Checked = false;
            chbDelhi.Checked = false;
            txtResult.Text = String.Empty;
        }
    }
}