package tk.airshipcraft.commonlib.utils.search;

import org.jetbrains.annotations.NotNull;

public interface StringDistance {

    double calculate(final byte @NotNull [] x, final byte @NotNull [] y);
}
