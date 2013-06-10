package network.game.packet.classes;

import network.util.stream.DataWatcher;
import network.util.stream.WatchableObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

public class Packet20NamedEntitySpawn extends Packet
{
    /** The entity ID, in this case it's the player ID. */
    public int entityId;

    /** The player's name. */
    public String name;

    /** The player's X position. */
    public int xPosition;

    /** The player's Y position. */
    public int yPosition;

    /** The player's Z position. */
    public int zPosition;

    /** The player's rotation. */
    public byte rotation;

    /** The player's pitch. */
    public byte pitch;

    /** The current item the player is holding. */
    public int currentItem;
    private DataWatcher metadata;
    private List field_73517_j;

    public Packet20NamedEntitySpawn() {}

    /**
     * Abstract. Reads the raw packet data from the data stream.
     */
    public void readPacketData(DataInputStream par1DataInputStream) throws IOException
    {
        this.entityId = par1DataInputStream.readInt();
        this.name = readString(par1DataInputStream, 16);
        this.xPosition = par1DataInputStream.readInt();
        this.yPosition = par1DataInputStream.readInt();
        this.zPosition = par1DataInputStream.readInt();
        this.rotation = par1DataInputStream.readByte();
        this.pitch = par1DataInputStream.readByte();
        this.currentItem = par1DataInputStream.readShort();
        this.field_73517_j = DataWatcher.readWatchableObjects(par1DataInputStream);
        this.metadata = new DataWatcher();
        for(Object obj : this.field_73517_j) {
            this.metadata.addObject(((WatchableObject)obj).getDataValueId(), ((WatchableObject)obj).getObject());
        }
    }

    /**
     * Abstract. Writes the raw packet data to the data stream.
     */
    public void writePacketData(DataOutputStream par1DataOutputStream) throws IOException
    {
        par1DataOutputStream.writeInt(this.entityId);
        writeString(this.name, par1DataOutputStream);
        par1DataOutputStream.writeInt(this.xPosition);
        par1DataOutputStream.writeInt(this.yPosition);
        par1DataOutputStream.writeInt(this.zPosition);
        par1DataOutputStream.writeByte(this.rotation);
        par1DataOutputStream.writeByte(this.pitch);
        par1DataOutputStream.writeShort(this.currentItem);
        this.metadata.writeWatchableObjects(par1DataOutputStream);
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
        return 28;
    }

    public List func_73509_c()
    {
        if (this.field_73517_j == null)
        {
            this.field_73517_j = this.metadata.func_75685_c();
        }

        return this.field_73517_j;
    }
}
