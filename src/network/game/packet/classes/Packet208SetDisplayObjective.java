package network.game.packet.classes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet208SetDisplayObjective extends Packet
{
    public int field_96481_a;
    public String field_96480_b;

    public Packet208SetDisplayObjective() {}
    /**
     * Abstract. Reads the raw packet data from the data stream.
     */
    public void readPacketData(DataInputStream par1DataInputStream) throws IOException
    {
        this.field_96481_a = par1DataInputStream.readByte();
        this.field_96480_b = readString(par1DataInputStream, 16);
    }

    /**
     * Abstract. Writes the raw packet data to the data stream.
     */
    public void writePacketData(DataOutputStream par1DataOutputStream) throws IOException
    {
        par1DataOutputStream.writeByte(this.field_96481_a);
        writeString(this.field_96480_b, par1DataOutputStream);
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
        return 3 + this.field_96480_b.length();
    }
}
