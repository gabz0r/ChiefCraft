package network.game.packet.classes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet206SetObjective extends Packet
{
    public String field_96484_a;
    public String field_96482_b;
    public int field_96483_c;

    public Packet206SetObjective() {}

    /**
     * Abstract. Reads the raw packet data from the data stream.
     */
    public void readPacketData(DataInputStream par1DataInputStream) throws IOException
    {
        this.field_96484_a = readString(par1DataInputStream, 16);
        this.field_96482_b = readString(par1DataInputStream, 32);
        this.field_96483_c = par1DataInputStream.readByte();
    }

    /**
     * Abstract. Writes the raw packet data to the data stream.
     */
    public void writePacketData(DataOutputStream par1DataOutputStream) throws IOException
    {
        writeString(this.field_96484_a, par1DataOutputStream);
        writeString(this.field_96482_b, par1DataOutputStream);
        par1DataOutputStream.writeByte(this.field_96483_c);
    }

    /**
     * Passes this Packet on to the NetHandler for processing.
     */
    public void processPacket()
    {
    }

    /**
     * Abstract. Return the size of the packet (not counting the header).
     */
    public int getPacketSize()
    {
        return 2 + this.field_96484_a.length() + 2 + this.field_96482_b.length() + 1;
    }
}
