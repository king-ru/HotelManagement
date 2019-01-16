package main.chenru.login;


/**
 * @program: HotelManagement
 * @description:返回注册人的信息
 * @author: Mrs.CeYi
 * @create: 2019-01-13 18:02
 **/
class GetInfoFromRegisterWnd {
    public String id;

    public String password;

    public String repassword;
    public String name;

    public String sex;

    public String tel;
    public GetInfoFromRegisterWnd()
    {
        id=CreatePanelForRegister.user_text.getText();
        password=CreatePanelForRegister.pwd_text.getText();
        repassword=CreatePanelForRegister.repwd_text.getText();
        name=CreatePanelForRegister.name_text.getText();
        sex=CreatePanelForRegister.sex_combox.getSelectedItem()+"";
        tel=CreatePanelForRegister.tel_text.getText();
    }
    public String getNo()
    {
        return id;
    }
    public String getPassword()
    {
        return password;
    }
    public String getRePassword(){

        return repassword;
    }
    public String getName(){

        return name;
    }
    public String getSex(){

        return sex;

    }
    public String getTel(){

        return tel;

    }
    public int check()
    {
        if (id.length()!=10){
            return 1;
        }
        else {
            if (password.equals(repassword))
            {
                if (tel.length()!=11){
                    return 3;
                }
                else return 0;
            }

            else{
                return 2;
            }
        }
    }
}


