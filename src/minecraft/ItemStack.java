package minecraft;


import network.util.nbt.*;

public final class ItemStack
{
    /** Size of the stack. */
    public int stackSize;

    /**
     * Number of animation frames to go when receiving an item (by walking into it, for example).
     */
    public int animationsToGo;

    /** ID of the item. */
    public int itemID;
    public boolean damageable;

    /**
     * A NBTTagMap containing data about an ItemStack. Can only be used for non stackable items
     */
    public NBTTagCompound stackTagCompound;

    /** Damage dealt to the item or number of use. Raise when using items. */
    private int itemDamage;

    public ItemStack(int par1, int par2, int par3)
    {
        this.stackSize = 0;
        this.itemID = par1;
        this.stackSize = par2;
        this.itemDamage = par3;
    }

    public static ItemStack loadItemStackFromNBT(NBTTagCompound par0NBTTagCompound)
    {
        ItemStack var1 = new ItemStack();
        var1.readFromNBT(par0NBTTagCompound);
        return var1;
    }

    private ItemStack()
    {
        this.stackSize = 0;
    }

    /**
     * Remove the argument from the stack size. Return a new stack object with argument size.
     */
    public ItemStack splitStack(int par1)
    {
        ItemStack var2 = new ItemStack(this.itemID, par1, this.itemDamage);

        if (this.stackTagCompound != null)
        {
            var2.stackTagCompound = (NBTTagCompound)this.stackTagCompound.copy();
        }

        this.stackSize -= par1;
        return var2;
    }

    /**
     * Write the stack fields to a NBT object. Return the new NBT object.
     */
    public NBTTagCompound writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        par1NBTTagCompound.setShort("id", (short)this.itemID);
        par1NBTTagCompound.setByte("Count", (byte)this.stackSize);
        par1NBTTagCompound.setShort("Damage", (short)this.itemDamage);

        if (this.stackTagCompound != null)
        {
            par1NBTTagCompound.setTag("tag", this.stackTagCompound);
        }

        return par1NBTTagCompound;
    }

    /**
     * Read the stack fields from a NBT object.
     */
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        this.itemID = par1NBTTagCompound.getShort("id");
        this.stackSize = par1NBTTagCompound.getByte("Count");
        this.itemDamage = par1NBTTagCompound.getShort("Damage");

        if (par1NBTTagCompound.hasKey("tag"))
        {
            this.stackTagCompound = par1NBTTagCompound.getCompoundTag("tag");
        }
    }

    /**
     * gets the damage of an itemstack, for displaying purposes
     */
    public int getItemDamageForDisplay()
    {
        return this.itemDamage;
    }

    /**
     * gets the damage of an itemstack
     */
    public int getItemDamage()
    {
        return this.itemDamage;
    }

    /**
     * Sets the item damage of the ItemStack.
     */
    public void setItemDamage(int par1)
    {
        this.itemDamage = par1;
    }

    /**
     * Returns a new stack with the same properties.
     */
    public ItemStack copy()
    {
        ItemStack var1 = new ItemStack(this.itemID, this.stackSize, this.itemDamage);

        if (this.stackTagCompound != null)
        {
            var1.stackTagCompound = (NBTTagCompound)this.stackTagCompound.copy();
        }

        return var1;
    }

    public static boolean areItemStackTagsEqual(ItemStack par0ItemStack, ItemStack par1ItemStack)
    {
        return par0ItemStack == null && par1ItemStack == null ? true : (par0ItemStack != null && par1ItemStack != null ? (par0ItemStack.stackTagCompound == null && par1ItemStack.stackTagCompound != null ? false : par0ItemStack.stackTagCompound == null || par0ItemStack.stackTagCompound.equals(par1ItemStack.stackTagCompound)) : false);
    }

    /**
     * compares ItemStack argument1 with ItemStack argument2; returns true if both ItemStacks are equal
     */
    public static boolean areItemStacksEqual(ItemStack par0ItemStack, ItemStack par1ItemStack)
    {
        return par0ItemStack == null && par1ItemStack == null ? true : (par0ItemStack != null && par1ItemStack != null ? par0ItemStack.isItemStackEqual(par1ItemStack) : false);
    }

    /**
     * compares ItemStack argument to the instance ItemStack; returns true if both ItemStacks are equal
     */
    private boolean isItemStackEqual(ItemStack par1ItemStack)
    {
        return this.stackSize != par1ItemStack.stackSize ? false : (this.itemID != par1ItemStack.itemID ? false : (this.itemDamage != par1ItemStack.itemDamage ? false : (this.stackTagCompound == null && par1ItemStack.stackTagCompound != null ? false : this.stackTagCompound == null || this.stackTagCompound.equals(par1ItemStack.stackTagCompound))));
    }

    /**
     * compares ItemStack argument to the instance ItemStack; returns true if the Items contained in both ItemStacks are
     * equal
     */
    public boolean isItemEqual(ItemStack par1ItemStack)
    {
        return this.itemID == par1ItemStack.itemID && this.itemDamage == par1ItemStack.itemDamage;
    }

    /**
     * Creates a copy of a ItemStack, a null parameters will return a null.
     */
    public static ItemStack copyItemStack(ItemStack par0ItemStack)
    {
        return par0ItemStack == null ? null : par0ItemStack.copy();
    }
    /**
     * Returns true if the ItemStack has an NBTTagCompound. Currently used to store enchantments.
     */
    public boolean hasTagCompound()
    {
        return this.stackTagCompound != null;
    }

    /**
     * Returns the NBTTagCompound of the ItemStack.
     */
    public NBTTagCompound getTagCompound()
    {
        return this.stackTagCompound;
    }

    public NBTTagList getEnchantmentTagList()
    {
        return this.stackTagCompound == null ? null : (NBTTagList)this.stackTagCompound.getTag("ench");
    }

    /**
     * Assigns a NBTTagCompound to the ItemStack, minecraft validates that only non-stackable items can have it.
     */
    public void setTagCompound(NBTTagCompound par1NBTTagCompound)
    {
        this.stackTagCompound = par1NBTTagCompound;
    }

    /**
     * Sets the item's name (used by anvil to rename the items).
     */
    public void setItemName(String par1Str)
    {
        if (this.stackTagCompound == null)
        {
            this.stackTagCompound = new NBTTagCompound();
        }

        if (!this.stackTagCompound.hasKey("display"))
        {
            this.stackTagCompound.setCompoundTag("display", new NBTTagCompound());
        }

        this.stackTagCompound.getCompoundTag("display").setString("Name", par1Str);
    }

    /**
     * Returns true if the itemstack has a display name
     */
    public boolean hasDisplayName()
    {
        return this.stackTagCompound == null ? false : (!this.stackTagCompound.hasKey("display") ? false : this.stackTagCompound.getCompoundTag("display").hasKey("Name"));
    }

    /**
     * True if the item has enchantment data
     */
    public boolean isItemEnchanted()
    {
        return this.stackTagCompound != null && this.stackTagCompound.hasKey("ench");
    }

    public void setTagInfo(String par1Str, NBTBase par2NBTBase)
    {
        if (this.stackTagCompound == null)
        {
            this.setTagCompound(new NBTTagCompound());
        }

        this.stackTagCompound.setTag(par1Str, par2NBTBase);
    }
}
