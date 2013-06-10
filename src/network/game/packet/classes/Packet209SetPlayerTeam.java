package network.game.packet.classes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Packet209SetPlayerTeam extends Packet
{
    public String field_96495_a = "";
    public String field_96493_b = "";
    public String field_96494_c = "";
    public String field_96491_d = "";
    public Collection field_96492_e = new ArrayList();
    public int field_96489_f = 0;
    public int field_98212_g;

    public Packet209SetPlayerTeam() {}
    
    /**
     * Abstract. Reads the raw packet data from the data stream.
     */
    public void readPacketData(DataInputStream par1DataInputStream) throws IOException
    {
        this.field_96495_a = readString(par1DataInputStream, 16);
        this.field_96489_f = par1DataInputStream.readByte();

        if (this.field_96489_f == 0 || this.field_96489_f == 2)
        {
            this.field_96493_b = readString(par1DataInputStream, 32);
            this.field_96494_c = readString(par1DataInputStream, 16);
            this.field_96491_d = readString(par1DataInputStream, 16);
            this.field_98212_g = par1DataInputStream.readByte();
        }

        if (this.field_96489_f == 0 || this.field_96489_f == 3 || this.field_96489_f == 4)
        {
            short var2 = par1DataInputStream.readShort();

            for (int var3 = 0; var3 < var2; ++var3)
            {
                this.field_96492_e.add(readString(par1DataInputStream, 16));
            }
        }
    }

    /**
     * Abstract. Writes the raw packet data to the data stream.
     */
    public void writePacketData(DataOutputStream par1DataOutputStream) throws IOException
    {
        writeString(this.field_96495_a, par1DataOutputStream);
        par1DataOutputStream.writeByte(this.field_96489_f);

        if (this.field_96489_f == 0 || this.field_96489_f == 2)
        {
            writeString(this.field_96493_b, par1DataOutputStream);
            writeString(this.field_96494_c, par1DataOutputStream);
            writeString(this.field_96491_d, par1DataOutputStream);
            par1DataOutputStream.writeByte(this.field_98212_g);
        }

        if (this.field_96489_f == 0 || this.field_96489_f == 3 || this.field_96489_f == 4)
        {
            par1DataOutputStream.writeShort(this.field_96492_e.size());
            Iterator var2 = this.field_96492_e.iterator();

            while (var2.hasNext())
            {
                String var3 = (String)var2.next();
                writeString(var3, par1DataOutputStream);
            }
        }
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
        return 3 + this.field_96495_a.length();
    }
}
