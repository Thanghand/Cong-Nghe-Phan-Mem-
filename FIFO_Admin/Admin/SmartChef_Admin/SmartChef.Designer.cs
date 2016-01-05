namespace SmartChef_Admin
{
    partial class SmartChef
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.tabControl = new System.Windows.Forms.TabControl();
            this.tabMeal = new System.Windows.Forms.TabPage();
            this.btnAll = new System.Windows.Forms.Button();
            this.txtsearch = new System.Windows.Forms.TextBox();
            this.btnsearch = new System.Windows.Forms.Button();
            this.dgvMeal = new System.Windows.Forms.DataGridView();
            this.panel1 = new System.Windows.Forms.Panel();
            this.btnbrowser = new System.Windows.Forms.Button();
            this.lbllink = new System.Windows.Forms.Label();
            this.txtlink = new System.Windows.Forms.TextBox();
            this.btnExport = new System.Windows.Forms.Button();
            this.txtSrcIP = new System.Windows.Forms.TextBox();
            this.btnChangeIP = new System.Windows.Forms.Button();
            this.txtDestIP = new System.Windows.Forms.TextBox();
            this.label18 = new System.Windows.Forms.Label();
            this.label16 = new System.Windows.Forms.Label();
            this.btnClear = new System.Windows.Forms.Button();
            this.pbMealPicture = new System.Windows.Forms.PictureBox();
            this.cbbDiet = new System.Windows.Forms.ComboBox();
            this.lblMealName = new System.Windows.Forms.Label();
            this.cbbMealTypeID = new System.Windows.Forms.ComboBox();
            this.txtMealName = new System.Windows.Forms.TextBox();
            this.btnInsertMeal = new System.Windows.Forms.Button();
            this.btnUpdateMeal = new System.Windows.Forms.Button();
            this.btnDeleteMeal = new System.Windows.Forms.Button();
            this.lblMealTypeID = new System.Windows.Forms.Label();
            this.lblTutorial = new System.Windows.Forms.Label();
            this.lblDescription = new System.Windows.Forms.Label();
            this.lblTimeID = new System.Windows.Forms.Label();
            this.lblMealPicture = new System.Windows.Forms.Label();
            this.txtTutorial = new System.Windows.Forms.TextBox();
            this.txtMealPicture = new System.Windows.Forms.TextBox();
            this.txtDescription = new System.Windows.Forms.TextBox();
            this.tabControl.SuspendLayout();
            this.tabMeal.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dgvMeal)).BeginInit();
            this.panel1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pbMealPicture)).BeginInit();
            this.SuspendLayout();
            // 
            // tabControl
            // 
            this.tabControl.Controls.Add(this.tabMeal);
            this.tabControl.Location = new System.Drawing.Point(1, 2);
            this.tabControl.Name = "tabControl";
            this.tabControl.SelectedIndex = 0;
            this.tabControl.Size = new System.Drawing.Size(1250, 703);
            this.tabControl.TabIndex = 0;
            // 
            // tabMeal
            // 
            this.tabMeal.Controls.Add(this.btnAll);
            this.tabMeal.Controls.Add(this.txtsearch);
            this.tabMeal.Controls.Add(this.btnsearch);
            this.tabMeal.Controls.Add(this.dgvMeal);
            this.tabMeal.Controls.Add(this.panel1);
            this.tabMeal.Location = new System.Drawing.Point(4, 22);
            this.tabMeal.Name = "tabMeal";
            this.tabMeal.Padding = new System.Windows.Forms.Padding(3);
            this.tabMeal.Size = new System.Drawing.Size(1242, 677);
            this.tabMeal.TabIndex = 0;
            this.tabMeal.Text = "Meal";
            this.tabMeal.UseVisualStyleBackColor = true;
            // 
            // btnAll
            // 
            this.btnAll.Location = new System.Drawing.Point(1122, 6);
            this.btnAll.Name = "btnAll";
            this.btnAll.Size = new System.Drawing.Size(75, 23);
            this.btnAll.TabIndex = 24;
            this.btnAll.Text = "All";
            this.btnAll.UseVisualStyleBackColor = true;
            this.btnAll.Click += new System.EventHandler(this.btnAll_Click);
            // 
            // txtsearch
            // 
            this.txtsearch.Location = new System.Drawing.Point(735, 9);
            this.txtsearch.Name = "txtsearch";
            this.txtsearch.Size = new System.Drawing.Size(213, 20);
            this.txtsearch.TabIndex = 23;
            // 
            // btnsearch
            // 
            this.btnsearch.Location = new System.Drawing.Point(640, 7);
            this.btnsearch.Name = "btnsearch";
            this.btnsearch.Size = new System.Drawing.Size(75, 23);
            this.btnsearch.TabIndex = 22;
            this.btnsearch.Text = "Search";
            this.btnsearch.UseVisualStyleBackColor = true;
            this.btnsearch.Click += new System.EventHandler(this.btnsearch_Click);
            // 
            // dgvMeal
            // 
            this.dgvMeal.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dgvMeal.Location = new System.Drawing.Point(635, 43);
            this.dgvMeal.Name = "dgvMeal";
            this.dgvMeal.ReadOnly = true;
            this.dgvMeal.SelectionMode = System.Windows.Forms.DataGridViewSelectionMode.FullRowSelect;
            this.dgvMeal.Size = new System.Drawing.Size(563, 638);
            this.dgvMeal.TabIndex = 3;
            this.dgvMeal.SelectionChanged += new System.EventHandler(this.dataGridView1_SelectionChanged);
            // 
            // panel1
            // 
            this.panel1.Controls.Add(this.btnbrowser);
            this.panel1.Controls.Add(this.lbllink);
            this.panel1.Controls.Add(this.txtlink);
            this.panel1.Controls.Add(this.btnExport);
            this.panel1.Controls.Add(this.txtSrcIP);
            this.panel1.Controls.Add(this.btnChangeIP);
            this.panel1.Controls.Add(this.txtDestIP);
            this.panel1.Controls.Add(this.label18);
            this.panel1.Controls.Add(this.label16);
            this.panel1.Controls.Add(this.btnClear);
            this.panel1.Controls.Add(this.pbMealPicture);
            this.panel1.Controls.Add(this.cbbDiet);
            this.panel1.Controls.Add(this.lblMealName);
            this.panel1.Controls.Add(this.cbbMealTypeID);
            this.panel1.Controls.Add(this.txtMealName);
            this.panel1.Controls.Add(this.btnInsertMeal);
            this.panel1.Controls.Add(this.btnUpdateMeal);
            this.panel1.Controls.Add(this.btnDeleteMeal);
            this.panel1.Controls.Add(this.lblMealTypeID);
            this.panel1.Controls.Add(this.lblTutorial);
            this.panel1.Controls.Add(this.lblDescription);
            this.panel1.Controls.Add(this.lblTimeID);
            this.panel1.Controls.Add(this.lblMealPicture);
            this.panel1.Controls.Add(this.txtTutorial);
            this.panel1.Controls.Add(this.txtMealPicture);
            this.panel1.Controls.Add(this.txtDescription);
            this.panel1.Location = new System.Drawing.Point(7, 6);
            this.panel1.Name = "panel1";
            this.panel1.Size = new System.Drawing.Size(622, 664);
            this.panel1.TabIndex = 21;
            // 
            // btnbrowser
            // 
            this.btnbrowser.Location = new System.Drawing.Point(351, 529);
            this.btnbrowser.Name = "btnbrowser";
            this.btnbrowser.Size = new System.Drawing.Size(75, 23);
            this.btnbrowser.TabIndex = 42;
            this.btnbrowser.Text = "Browser";
            this.btnbrowser.UseVisualStyleBackColor = true;
            this.btnbrowser.Click += new System.EventHandler(this.btnbrowser_Click);
            // 
            // lbllink
            // 
            this.lbllink.AutoSize = true;
            this.lbllink.Location = new System.Drawing.Point(276, 492);
            this.lbllink.Name = "lbllink";
            this.lbllink.Size = new System.Drawing.Size(60, 13);
            this.lbllink.TabIndex = 41;
            this.lbllink.Text = "Đường dẫn";
            // 
            // txtlink
            // 
            this.txtlink.Location = new System.Drawing.Point(351, 489);
            this.txtlink.Name = "txtlink";
            this.txtlink.Size = new System.Drawing.Size(257, 20);
            this.txtlink.TabIndex = 40;
            // 
            // btnExport
            // 
            this.btnExport.Location = new System.Drawing.Point(432, 529);
            this.btnExport.Name = "btnExport";
            this.btnExport.Size = new System.Drawing.Size(75, 23);
            this.btnExport.TabIndex = 25;
            this.btnExport.Text = "Export";
            this.btnExport.UseVisualStyleBackColor = true;
            this.btnExport.Click += new System.EventHandler(this.btnExport_Click);
            // 
            // txtSrcIP
            // 
            this.txtSrcIP.Location = new System.Drawing.Point(71, 489);
            this.txtSrcIP.Name = "txtSrcIP";
            this.txtSrcIP.Size = new System.Drawing.Size(135, 20);
            this.txtSrcIP.TabIndex = 39;
            // 
            // btnChangeIP
            // 
            this.btnChangeIP.Location = new System.Drawing.Point(71, 573);
            this.btnChangeIP.Name = "btnChangeIP";
            this.btnChangeIP.Size = new System.Drawing.Size(135, 23);
            this.btnChangeIP.TabIndex = 38;
            this.btnChangeIP.Text = "changeIP";
            this.btnChangeIP.UseVisualStyleBackColor = true;
            this.btnChangeIP.Click += new System.EventHandler(this.btnChangeIP_Click_1);
            // 
            // txtDestIP
            // 
            this.txtDestIP.Location = new System.Drawing.Point(71, 531);
            this.txtDestIP.Name = "txtDestIP";
            this.txtDestIP.Size = new System.Drawing.Size(135, 20);
            this.txtDestIP.TabIndex = 37;
            // 
            // label18
            // 
            this.label18.AutoSize = true;
            this.label18.Location = new System.Drawing.Point(23, 492);
            this.label18.Name = "label18";
            this.label18.Size = new System.Drawing.Size(33, 13);
            this.label18.TabIndex = 35;
            this.label18.Text = "SrcIP";
            // 
            // label16
            // 
            this.label16.AutoSize = true;
            this.label16.Location = new System.Drawing.Point(23, 534);
            this.label16.Name = "label16";
            this.label16.Size = new System.Drawing.Size(42, 13);
            this.label16.TabIndex = 36;
            this.label16.Text = "Dest IP";
            // 
            // btnClear
            // 
            this.btnClear.Location = new System.Drawing.Point(98, 9);
            this.btnClear.Name = "btnClear";
            this.btnClear.Size = new System.Drawing.Size(75, 23);
            this.btnClear.TabIndex = 29;
            this.btnClear.Text = "Clear";
            this.btnClear.UseVisualStyleBackColor = true;
            this.btnClear.Click += new System.EventHandler(this.btnClear_Click);
            // 
            // pbMealPicture
            // 
            this.pbMealPicture.Location = new System.Drawing.Point(422, 9);
            this.pbMealPicture.Name = "pbMealPicture";
            this.pbMealPicture.Size = new System.Drawing.Size(146, 130);
            this.pbMealPicture.TabIndex = 24;
            this.pbMealPicture.TabStop = false;
            this.pbMealPicture.Click += new System.EventHandler(this.pbMealPicture_Click);
            // 
            // cbbDiet
            // 
            this.cbbDiet.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cbbDiet.FormattingEnabled = true;
            this.cbbDiet.Items.AddRange(new object[] {
            "Unidentified",
            "diet",
            "vegetable food",
            "gain weight",
            "weight-lifting"});
            this.cbbDiet.Location = new System.Drawing.Point(97, 112);
            this.cbbDiet.Name = "cbbDiet";
            this.cbbDiet.Size = new System.Drawing.Size(237, 21);
            this.cbbDiet.TabIndex = 20;
            // 
            // lblMealName
            // 
            this.lblMealName.AutoSize = true;
            this.lblMealName.Location = new System.Drawing.Point(23, 45);
            this.lblMealName.Name = "lblMealName";
            this.lblMealName.Size = new System.Drawing.Size(67, 13);
            this.lblMealName.TabIndex = 4;
            this.lblMealName.Text = "Tên món ăn:";
            // 
            // cbbMealTypeID
            // 
            this.cbbMealTypeID.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cbbMealTypeID.FormattingEnabled = true;
            this.cbbMealTypeID.Items.AddRange(new object[] {
            "Unidentified",
            "cow",
            "pig",
            "chicken",
            "duck",
            "fish",
            "crab"});
            this.cbbMealTypeID.Location = new System.Drawing.Point(97, 74);
            this.cbbMealTypeID.Name = "cbbMealTypeID";
            this.cbbMealTypeID.Size = new System.Drawing.Size(237, 21);
            this.cbbMealTypeID.TabIndex = 19;
            // 
            // txtMealName
            // 
            this.txtMealName.Location = new System.Drawing.Point(98, 45);
            this.txtMealName.Name = "txtMealName";
            this.txtMealName.Size = new System.Drawing.Size(237, 20);
            this.txtMealName.TabIndex = 1;
            // 
            // btnInsertMeal
            // 
            this.btnInsertMeal.Location = new System.Drawing.Point(179, 9);
            this.btnInsertMeal.Name = "btnInsertMeal";
            this.btnInsertMeal.Size = new System.Drawing.Size(75, 23);
            this.btnInsertMeal.TabIndex = 2;
            this.btnInsertMeal.Text = "Thêm";
            this.btnInsertMeal.UseVisualStyleBackColor = true;
            this.btnInsertMeal.Click += new System.EventHandler(this.btnInsertMeal_Click);
            // 
            // btnUpdateMeal
            // 
            this.btnUpdateMeal.Location = new System.Drawing.Point(260, 8);
            this.btnUpdateMeal.Name = "btnUpdateMeal";
            this.btnUpdateMeal.Size = new System.Drawing.Size(75, 23);
            this.btnUpdateMeal.TabIndex = 2;
            this.btnUpdateMeal.Text = "Sửa";
            this.btnUpdateMeal.UseVisualStyleBackColor = true;
            this.btnUpdateMeal.Click += new System.EventHandler(this.btnUpdateMeal_Click);
            // 
            // btnDeleteMeal
            // 
            this.btnDeleteMeal.Location = new System.Drawing.Point(341, 9);
            this.btnDeleteMeal.Name = "btnDeleteMeal";
            this.btnDeleteMeal.Size = new System.Drawing.Size(75, 23);
            this.btnDeleteMeal.TabIndex = 2;
            this.btnDeleteMeal.Text = "Xóa";
            this.btnDeleteMeal.UseVisualStyleBackColor = true;
            this.btnDeleteMeal.Click += new System.EventHandler(this.btnDeleteMeal_Click);
            // 
            // lblMealTypeID
            // 
            this.lblMealTypeID.AutoSize = true;
            this.lblMealTypeID.Location = new System.Drawing.Point(23, 82);
            this.lblMealTypeID.Name = "lblMealTypeID";
            this.lblMealTypeID.Size = new System.Drawing.Size(68, 13);
            this.lblMealTypeID.TabIndex = 0;
            this.lblMealTypeID.Text = "Loại món ăn:";
            // 
            // lblTutorial
            // 
            this.lblTutorial.AutoSize = true;
            this.lblTutorial.Location = new System.Drawing.Point(23, 306);
            this.lblTutorial.Name = "lblTutorial";
            this.lblTutorial.Size = new System.Drawing.Size(99, 13);
            this.lblTutorial.TabIndex = 8;
            this.lblTutorial.Text = "Hướng dẫn nấu ăn:";
            // 
            // lblDescription
            // 
            this.lblDescription.AutoSize = true;
            this.lblDescription.Location = new System.Drawing.Point(23, 209);
            this.lblDescription.Name = "lblDescription";
            this.lblDescription.Size = new System.Drawing.Size(37, 13);
            this.lblDescription.TabIndex = 7;
            this.lblDescription.Text = "Mô tả:";
            // 
            // lblTimeID
            // 
            this.lblTimeID.AutoSize = true;
            this.lblTimeID.Location = new System.Drawing.Point(23, 115);
            this.lblTimeID.Name = "lblTimeID";
            this.lblTimeID.Size = new System.Drawing.Size(57, 13);
            this.lblTimeID.TabIndex = 9;
            this.lblTimeID.Text = "Chế độ ăn";
            // 
            // lblMealPicture
            // 
            this.lblMealPicture.AutoSize = true;
            this.lblMealPicture.Location = new System.Drawing.Point(23, 158);
            this.lblMealPicture.Name = "lblMealPicture";
            this.lblMealPicture.Size = new System.Drawing.Size(91, 13);
            this.lblMealPicture.TabIndex = 5;
            this.lblMealPicture.Text = "Hình ảnh món ăn:";
            // 
            // txtTutorial
            // 
            this.txtTutorial.Location = new System.Drawing.Point(26, 322);
            this.txtTutorial.Multiline = true;
            this.txtTutorial.Name = "txtTutorial";
            this.txtTutorial.Size = new System.Drawing.Size(582, 148);
            this.txtTutorial.TabIndex = 1;
            // 
            // txtMealPicture
            // 
            this.txtMealPicture.Location = new System.Drawing.Point(26, 186);
            this.txtMealPicture.Name = "txtMealPicture";
            this.txtMealPicture.Size = new System.Drawing.Size(582, 20);
            this.txtMealPicture.TabIndex = 1;
            // 
            // txtDescription
            // 
            this.txtDescription.Location = new System.Drawing.Point(26, 225);
            this.txtDescription.Multiline = true;
            this.txtDescription.Name = "txtDescription";
            this.txtDescription.Size = new System.Drawing.Size(582, 78);
            this.txtDescription.TabIndex = 1;
            // 
            // SmartChef
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1232, 706);
            this.Controls.Add(this.tabControl);
            this.Name = "SmartChef";
            this.Text = "FIFO";
            this.FormClosed += new System.Windows.Forms.FormClosedEventHandler(this.Form1_FormClosed);
            this.Load += new System.EventHandler(this.Form1_Load);
            this.tabControl.ResumeLayout(false);
            this.tabMeal.ResumeLayout(false);
            this.tabMeal.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dgvMeal)).EndInit();
            this.panel1.ResumeLayout(false);
            this.panel1.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pbMealPicture)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.TabControl tabControl;
        private System.Windows.Forms.TabPage tabMeal;
        private System.Windows.Forms.Label lblTimeID;
        private System.Windows.Forms.Label lblTutorial;
        private System.Windows.Forms.Label lblDescription;
        private System.Windows.Forms.Label lblMealPicture;
        private System.Windows.Forms.Label lblMealName;
        private System.Windows.Forms.DataGridView dgvMeal;
        private System.Windows.Forms.Button btnDeleteMeal;
        private System.Windows.Forms.Button btnUpdateMeal;
        private System.Windows.Forms.Button btnInsertMeal;
        private System.Windows.Forms.Label lblMealTypeID;
        private System.Windows.Forms.TextBox txtTutorial;
        private System.Windows.Forms.TextBox txtMealPicture;
        private System.Windows.Forms.TextBox txtDescription;
        private System.Windows.Forms.TextBox txtMealName;
        private System.Windows.Forms.ComboBox cbbMealTypeID;
        private System.Windows.Forms.ComboBox cbbDiet;
        private System.Windows.Forms.Panel panel1;
        private System.Windows.Forms.PictureBox pbMealPicture;
        private System.Windows.Forms.Button btnClear;
        private System.Windows.Forms.Button btnsearch;
        private System.Windows.Forms.TextBox txtSrcIP;
        private System.Windows.Forms.Button btnChangeIP;
        private System.Windows.Forms.TextBox txtDestIP;
        private System.Windows.Forms.Label label18;
        private System.Windows.Forms.Label label16;
        private System.Windows.Forms.TextBox txtsearch;
        private System.Windows.Forms.Button btnAll;
        private System.Windows.Forms.Button btnExport;
        private System.Windows.Forms.Label lbllink;
        private System.Windows.Forms.TextBox txtlink;
        private System.Windows.Forms.Button btnbrowser;
    }
}

