package network.game.packet.classes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet71Weather extends Packet
{
    public int entityID;
    public int posX;
    public int posY;
    public int posZ;
    public int isLightningBolt;

    public Packet71Weather() {}

    /**
     * Abstract. Reads the raw packet data from the data stream.
     */
    public void readPacketData(DataInputStream par1DataInputStream) throws IOException
    {
        this.entityID = par1DataInputStream.readInt();
        this.isLightningBolt = par1DataInputStream.readByte();
        this.posX = par1DataInputStream.readInt();
        this.posY = par1DataInputStream.readInt();
        this.posZ = par1DataInputStream.readInt();
    }

    /**
     * Abstract. Writes the raw packet data to the data stream.
     */
    public void writePacketData(DataOutputStream par1DataOutputStream) throws IOException
    {
        par1DataOutputStream.writeInt(this.entityID);
        par1DataOutputStream.writeByte(this.isLightningBolt);
        par1DataOutputStream.writeInt(this.posX);
        par1DataOutputStream.writeInt(this.posY);
        par1DataOutputStream.writeInt(this.posZ);
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
        return 17;
    }
}
