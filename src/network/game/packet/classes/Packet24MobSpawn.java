package network.game.packet.classes;

import network.util.stream.DataWatcher;
import network.util.stream.WatchableObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

public class Packet24MobSpawn extends Packet
{
    /** The entity ID. */
    public int entityId;

    /** The type of mob. */
    public int type;

    /** The X position of the entity. */
    public int xPosition;

    /** The Y position of the entity. */
    public int yPosition;

    /** The Z position of the entity. */
    public int zPosition;
    public int velocityX;
    public int velocityY;
    public int velocityZ;

    /** The yaw of the entity. */
    public byte yaw;

    /** The pitch of the entity. */
    public byte pitch;

    /** The yaw of the entity's head. */
    public byte headYaw;

    /** Indexed metadata for Mob, terminated by 0x7F */
    private DataWatcher metaData;
    private List metadata;

    public Packet24MobSpawn() {}
    
    /**
     * Abstract. Reads the raw packet data from the data stream.
     */
    public void readPacketData(DataInputStream par1DataInputStream) throws IOException
    {
        this.entityId = par1DataInputStream.readInt();
        this.type = par1DataInputStream.readByte() & 255;
        this.xPosition = par1DataInputStream.readInt();
        this.yPosition = par1DataInputStream.readInt();
        this.zPosition = par1DataInputStream.readInt();
        this.yaw = par1DataInputStream.readByte();
        this.pitch = par1DataInputStream.readByte();
        this.headYaw = par1DataInputStream.readByte();
        this.velocityX = par1DataInputStream.readShort();
        this.velocityY = par1DataInputStream.readShort();
        this.velocityZ = par1DataInputStream.readShort();
        this.metadata = DataWatcher.readWatchableObjects(par1DataInputStream);
        this.metaData = new DataWatcher();
        for(Object obj : this.metadata) {
            this.metaData.addObject(((WatchableObject)obj).getDataValueId(), ((WatchableObject)obj).getObject());
        }
    }

    /**
     * Abstract. Writes the raw packet data to the data stream.
     */
    public void writePacketData(DataOutputStream par1DataOutputStream) throws IOException
    {
        par1DataOutputStream.writeInt(this.entityId);
        par1DataOutputStream.writeByte(this.type & 255);
        par1DataOutputStream.writeInt(this.xPosition);
        par1DataOutputStream.writeInt(this.yPosition);
        par1DataOutputStream.writeInt(this.zPosition);
        par1DataOutputStream.writeByte(this.yaw);
        par1DataOutputStream.writeByte(this.pitch);
        par1DataOutputStream.writeByte(this.headYaw);
        par1DataOutputStream.writeShort(this.velocityX);
        par1DataOutputStream.writeShort(this.velocityY);
        par1DataOutputStream.writeShort(this.velocityZ);
        this.metaData.writeWatchableObjects(par1DataOutputStream);
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
        return 26;
    }

    public List getMetadata()
    {
        if (this.metadata == null)
        {
            this.metadata = this.metaData.func_75685_c();
        }

        return this.metadata;
    }
}
