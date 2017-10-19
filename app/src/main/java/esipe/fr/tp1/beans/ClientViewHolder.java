package esipe.fr.tp1.beans;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by zouhairhajji on 05/10/2017.
 */

public class ClientViewHolder {

    public TextView pseudo;
    public TextView text;
    public ImageView avatar;
    public Client client;


    public void setPseudo(TextView pseudo) {
        this.pseudo = pseudo;
    }

    public void setText(TextView text) {
        this.text = text;
    }

    public void setAvatar(ImageView avatar) {
        this.avatar = avatar;
    }

    public TextView getPseudo() {
        return pseudo;
    }

    public TextView getText() {
        return text;
    }

    public ImageView getAvatar() {
        return avatar;
    }
}
