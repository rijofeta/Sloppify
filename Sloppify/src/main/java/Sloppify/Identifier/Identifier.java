package Sloppify.Identifier;

import com.devskiller.friendly_id.FriendlyId;

public class Identifier {

    public static String createIdentifier() {
        return FriendlyId.createFriendlyId();
    }
}
