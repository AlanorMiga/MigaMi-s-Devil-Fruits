package ttv.migami.mdf.network.message;

import com.mrcrayfish.framework.api.network.MessageContext;
import com.mrcrayfish.framework.api.network.message.PlayMessage;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import ttv.migami.mdf.common.network.FireworkFruitHandler;

public class S2SFireworkFruit extends PlayMessage<S2SFireworkFruit>
{
    private int move;

    public S2SFireworkFruit() {}

    public S2SFireworkFruit(int move)
    {
        this.move = move;
    }

    @Override
    public void encode(S2SFireworkFruit message, FriendlyByteBuf buffer)
    {
        buffer.writeInt(message.move);
    }

    @Override
    public S2SFireworkFruit decode(FriendlyByteBuf buffer)
    {
        return new S2SFireworkFruit(buffer.readInt());
    }

    @Override
    public void handle(S2SFireworkFruit message, MessageContext context)
    {
        context.execute(() ->
        {
            ServerPlayer player = context.getPlayer();
            if(player != null && !player.isSpectator())
            {
                FireworkFruitHandler.moveHandler(player, message.move);
            }
        });
        context.setHandled(true);
    }
}
