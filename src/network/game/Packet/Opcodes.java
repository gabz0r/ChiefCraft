package network.game.packet;

/**
 * Created with IntelliJ IDEA.
 * User: Gabriel
 * Date: 10.06.13
 * Time: 11:26
 * To change this template use File | Settings | File Templates.
 */
public class Opcodes {
    public static int KEEP_ALIVE = 0x00;
    public static int LOGIN_REQUEST = 0x01;
    public static int HANDSHAKE = 0x02;
    public static int CHAT_MESSAGE = 0x03;
    public static int TIME_UPDATE = 0x04;
    public static int ENTITY_EQUIPMENT = 0x05;
    public static int SPAWN_POSITION = 0x06;
    public static int USE_ENTITY = 0x07;
    public static int UPDATE_HEALTH = 0x08;
    public static int RESPAWN = 0x09;
    public static int PLAYER = 0x0A;
    public static int PLAYER_POSITION = 0x0B;
    public static int PLAYER_LOOK = 0x0C;
    public static int PLAYER_POS_LOOK = 0x0D;
    public static int PLAYER_DIGGING = 0x0E;
    public static int PLAYER_BLOCK_PLACEMENT = 0x0F;
    public static int HELD_ITEM_CHANGE = 0x10;
    public static int USE_BED = 0x11;
    public static int ANIMATION = 0x12;
    public static int ENTITY_ACTION = 0x13;
    public static int SPAWN_NAMED_ENTITY = 0x14;
    public static int COLLECT_ITEM = 0x15;
    public static int SPAWN_OBJECT_VEHICLE = 0x17;
    public static int SPAWN_MOB = 0x18;
    public static int SPAWN_PAINTING = 0x19;
    public static int SPAWN_EXP_ORB = 0x1A;
    public static int ENTITY_VELOCITY = 0x1C;
    public static int DESTROY_ENTITY = 0x1D;
    public static int ENTITY = 0x1E;
    public static int ENTITY_REL_MOVEMENT = 0x1F;
    public static int ENTITY_LOOK = 0x20;
    public static int ENTITY_REL_POS_LOOK = 0x21;
    public static int ENTITY_TELEPORT = 0x22;
    public static int ENTITY_HEAD_LOOK = 0x23;
    public static int ENTITY_STATUS = 0x26;
    public static int ATTACH_ENTITY = 0x27;
    public static int ENTITY_METADATA = 0x28;
    public static int ENTITY_EFFECT = 0x29;
    public static int REMOVE_ENTITY_EFFECT = 0x2A;
    public static int SET_EXPERIENCE = 0x2B;
    public static int CHUNK_DATA = 0x33;
    public static int MULTI_BLOCK_CHANGE = 0x34;
    public static int BLOCK_CHANGE = 0x35;
    public static int BLOCK_ACTION = 0x36;
    public static int BLOCK_BREAK_ANIMATION = 0x37;
    public static int MAP_CHUNK_BULK = 0x38;
    public static int EXPLOSION = 0x3C;
    public static int SOUND_PARTICLE_EFFECT = 0x3D;
    public static int NAME_SOUND_EFFECT = 0x3E;
    public static int PARTICLE = 0x3F;
    public static int CHANGE_GAME_STATE = 0x46;
    public static int SPAWN_GLOBAL_ENTITY = 0x47;
    public static int OPEN_WINDOW = 0x64;
    public static int CLOSE_WINDOW = 0x65;
    public static int CLICK_WINDOW = 0x66;
    public static int SET_SLOT = 0x67;
    public static int SET_WINDOW_ITEMS = 0x68;
    public static int UPDATE_WINDOW_PROPERTY = 0x69;
    public static int CONFIRM_TRANSACTION = 0x6A;
    public static int CREATIVE_INVENTORY_ACTION = 0x6B;
    public static int ENCHANT_ITEM = 0x6C;
    public static int UPDATE_SIGN = 0x82;
    public static int ITEM_DATA = 0x83;
    public static int UPDATE_TILE_ENTITY = 0x84;
    public static int INC_STATISTICS = 0xC8;
    public static int PLAYER_LIST_ITEM = 0xC9;
    public static int PLAYER_ABILITIES = 0xCA;
    public static int TAB_COMPLETE = 0xCB;
    public static int CLIENT_SETTINGS = 0xCC;
    public static int CLIENT_STATS = 0xCD;
    public static int SCOREBOARD_OBJECTIVE = 0xCE;
    public static int UPDATE_SCOREBOARD = 0xCF;
    public static int DISPLAY_SCOREBOARD = 0xD0;
    public static int TEAMS = 0xD1;
    public static int PLUGIN_MESSAGE = 0xFA;
    public static int ENCRYPTION_KEY_RESPONSE = 0xFC;
    public static int ENCRYPTION_KEY_REQUEST = 0xFD;
    public static int SERVER_LIST_PING = 0xFE;
    public static int DISCONNECT_KICK = 0xFF;
}
