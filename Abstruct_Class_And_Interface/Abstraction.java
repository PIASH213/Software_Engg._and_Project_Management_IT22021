interface Switch {
    void click();
}

abstract class BatterySwitch implements Switch {
    void checkBattery() {
        System.out.println("Battery is full.");
    }
}

class SimpleSwitch implements Switch {
    public void click() {
        System.out.println("Light turned ON!");
    }
}

class RemoteSwitch extends BatterySwitch {
    public void click() {
        checkBattery();
        System.out.println("Remote light turned ON!");
    }
}

public class Abstraction {
    public static void main(String[] args) {
        Switch s1 = new SimpleSwitch();
        s1.click();

        Switch s2 = new RemoteSwitch();
        s2.click();
    }
}
