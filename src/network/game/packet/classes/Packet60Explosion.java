package network.game.packet.classes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet60Explosion extends Packet
{
    public double explosionX;
    public double explosionY;
    public double explosionZ;
    public float explosionSize;
    private float field_73610_f;
    private float field_73611_g;
    private float field_73617_h;
    private int[] x_offset;
    private int[] y_offset;
    private int[] z_offset;
    
    public Packet60Explosion() {}

    /**
     * Abstract. Reads the raw packet data from the data stream.
     */
    public void readPacketData(DataInputStream par1DataInputStream) throws IOException
    {
        this.explosionX = par1DataInputStream.readDouble();
        this.explosionY = par1DataInputStream.readDouble();
        this.explosionZ = par1DataInputStream.readDouble();
        this.explosionSize = par1DataInputStream.readFloat();
        int var2 = par1DataInputStream.readInt();
        this.x_offset = new int[var2];
        this.y_offset = new int[var2];
        this.z_offset = new int[var2];

        for (int var6 = 0; var6 < var2; var6++)
        {
            x_offset[var6] = par1DataInputStream.readByte();
            y_offset[var6] = par1DataInputStream.readByte();
            z_offset[var6] = par1DataInputStream.readByte();
        }

        this.field_73610_f = par1DataInputStream.readFloat();
        this.field_73611_g = par1DataInputStream.readFloat();
        this.field_73617_h = par1DataInputStream.readFloat();
    }

    /**
     * Abstract. Writes the raw packet data to the data stream.
     */
    public void writePacketData(DataOutputStream par1DataOutputStream) throws IOException
    {
        par1DataOutputStream.writeDouble(explosionX);
        par1DataOutputStream.writeDouble(explosionY);
        par1DataOutputStream.writeDouble(explosionZ);
        par1DataOutputStream.writeFloat(explosionSize);
        par1DataOutputStream.writeInt(x_offset.length);
        for (int var6 = 0; var6 < x_offset.length; var6++)
        {
            par1DataOutputStream.writeByte(x_offset[var6]);
            par1DataOutputStream.writeByte(y_offset[var6]);
            par1DataOutputStream.writeByte(z_offset[var6]);
        }
        par1DataOutputStream.writeFloat(field_73610_f);
        par1DataOutputStream.writeFloat(field_73611_g);
        par1DataOutputStream.writeFloat(field_73617_h);
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
        return 35;
    }

    public float func_73607_d()
    {
        return this.field_73610_f;
    }

    public float func_73609_f()
    {
        return this.field_73611_g;
    }

    public float func_73608_g()
    {
        return this.field_73617_h;
    }
}
