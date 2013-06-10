package network.game.packet.classes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet101CloseWindow extends Packet
{
    public int windowId;

    public Packet101CloseWindow() {}

    public Packet101CloseWindow(int par1)
    {
        this.windowId = par1;
    }

    /**
     * Passes this Packet on to the NetHandler for processing.
     */
    @Override
    public void processPacket()
    {
       
    }

    /**
     * Abstract. Reads the raw packet data from the data stream.
     */
    public void readPacketData(DataInputStream par1DataInputStream) throws IOException
    {
        this.windowId = par1DataInputStream.readByte();
    }

    /**
     * Abstract. Writes the raw packet data to the data stream.
     */
    public void writePacketData(DataOutputStream par1DataOutputStream) throws IOException
    {
        par1DataOutputStream.writeByte(this.windowId);
    }

    /**
     * Abstract. Return the size of the packet (not counting the header).
     */
    public int getPacketSize()
    {
        return 1;
    }
}
