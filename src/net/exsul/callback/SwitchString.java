package net.exsul.callback;

public class SwitchString extends Switch<String> {
    @Override
    protected boolean Equal( final String a, final String b ) {
        return a.equalsIgnoreCase(b);
    }

}
