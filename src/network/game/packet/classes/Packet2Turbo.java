package network.game.packet.classes;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet2Turbo extends Packet {
    
    public int getPacketId() {
        return 2;
    }
    
    public void readPacketData(DataInputStream in) {
    }
    
    public void writePacketData(DataOutputStream out) {
    }
    
    public int getPacketSize() {
        return 0;
    }
    
    public void processPacket() {
    }
    
}