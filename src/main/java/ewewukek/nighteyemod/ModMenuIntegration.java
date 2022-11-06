package ewewukek.nighteyemod;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(new TranslatableText("nighteyemod.options.title"));
            ConfigEntryBuilder entryBuilder = builder.entryBuilder();

            ConfigCategory category = builder.getOrCreateCategory(new LiteralText("category"));

            category.addEntry(entryBuilder.startIntSlider(
                new TranslatableText("nighteyemod.options.strength"), Config.strength, 0, 100)
                .setSaveConsumer(value -> Config.strength = value)
                .setDefaultValue(Config.STRENGTH_DEFAULT)
                .build());

            builder.setSavingRunnable(() -> {
                Config.save();
                NightEyeMod.updateStrength();
            });

            return builder.build();

        };
    }
}
