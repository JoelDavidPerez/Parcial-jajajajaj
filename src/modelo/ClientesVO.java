
package modelo;

public class ClientesVO {
   
    private String ip;
    private String nickname;

    public ClientesVO() {   
    }


    public ClientesVO(String ip, String nickname) {
        this.ip = ip;
        this.nickname = nickname;
    }

    public String getIp(){
        return ip;
    }

    public void setIp(String ip){
        this.ip = ip;
    }

    public String getNick() {
        return nickname;
    }

    public void setNick(String nickname) {
        this.nickname = nickname;
    }

}

