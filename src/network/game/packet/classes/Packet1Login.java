package network.game.packet.classes;

import minecraft.Player;
import minecraft.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet1Login extends Packet {

    /**
     * The player's entity ID
     */
    public int clientEntityId = 0;
    public String terrainType;
    public boolean hardcoreMode;
    public int gameType;
    /**
     * -1: The Nether, 0: The Overworld, 1: The End
     */
    public int dimension;
    /**
     * The difficulty setting byte.
     */
    public byte difficultySetting;
    /**
     * Defaults to 128
     */
    public byte worldHeight;
    /**
     * The maximum players.
     */
    public byte maxPlayers;

    public Packet1Login() {
    }

    /**
     * Abstract. Reads the raw packet data from the data stream.
     */
    public void readPacketData(DataInputStream par1DataInputStream) throws IOException {
        this.clientEntityId = par1DataInputStream.readInt();
        String var2 = readString(par1DataInputStream, 16);
        this.terrainType = var2;

        byte var3 = par1DataInputStream.readByte();
        this.hardcoreMode = (var3 & 8) == 8;
        int var4 = var3 & -9;
        this.gameType = var4;
        this.dimension = par1DataInputStream.readByte();
        this.difficultySetting = par1DataInputStream.readByte();
        this.worldHeight = par1DataInputStream.readByte();
        this.maxPlayers = par1DataInputStream.readByte();
    }

    /**
     * Abstract. Writes the raw packet data to the data stream.
     */
    public void writePacketData(DataOutputStream par1DataOutputStream) throws IOException {
        par1DataOutputStream.writeInt(this.clientEntityId);
        writeString(this.terrainType == null ? "" : this.terrainType, par1DataOutputStream);
        int var2 = this.gameType;

        if (this.hardcoreMode) {
            var2 |= 8;
        }

        par1DataOutputStream.writeByte(var2);
        par1DataOutputStream.writeByte(this.dimension);
        par1DataOutputStream.writeByte(this.difficultySetting);
        par1DataOutputStream.writeByte(this.worldHeight);
        par1DataOutputStream.writeByte(this.maxPlayers);
    }

    /**
     * Passes this Packet on to the NetHandler for processing.
     */
    public void processPacket() {
        Player.getPlayer().setEntityID(clientEntityId);
        Player.getPlayer().setName(User.getUser().getUsername());
        /*
        System.out.println("- Entity ID " + clientEntityId);
        System.out.println("- Difficulty " + difficultySetting);
        System.out.println("- Dimension " + dimension);
        System.out.println("- Game Type " + gameType);
        System.out.println("- Hardcore? " + hardcoreMode);
        System.out.println("- Max Players " + maxPlayers);
        */
    }

    /**
     * Abstract. Return the size of the packet (not counting the header).
     */
    public int getPacketSize() {
        int var1 = 0;

        if (this.terrainType != null) {
            var1 = this.terrainType.length();
        }

        return 6 + 2 * var1 + 4 + 4 + 1 + 1 + 1;
    }
}
