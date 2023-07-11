using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data;
using System.Data.SqlClient;

namespace WebApp1_Form
{
    public partial class WebForm5 : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }
        string conString = "Data Source=localhost;Initial Catalog=project_sales;User ID=root;Password=Dasf180999";

        protected void Button1_Click(object sender, EventArgs e)
        {
            // Creating the object of SqlConnection class
            SqlConnection conObject = new SqlConnection(conString);
            try
            {
                conObject.Open();
                if (conObject.State == ConnectionState.Open)
                    Label1.Text = "Database Connection is Open";
            }
            catch (SqlException sqlexception)
            {
                Label1.Text = "ERROR ::" + sqlexception.Message;
            }
            catch (Exception ex)
            {
                Label1.Text = "ERROR ::" + ex.Message;
            }
            finally
            {
                conObject.Close();
            }
        }
    }
}