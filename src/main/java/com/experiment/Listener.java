package com.experiment;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;


import java.util.List;

public class Listener extends ListenerAdapter {
String searchAnime;
String searchGenre;

    MovieAPI movieAPI = new MovieAPI();

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {


        if (event.getMessage().getContentRaw().equals("!hi")) {
            event.getChannel().sendMessage("Hello!").queue();
        }

        if (event.getMessage().getContentRaw().equals("!info")) {
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setTitle("> Commands");
            embedBuilder.setDescription("!anime [anime name]" + "\n" + "!genre [genre name]");
            event.getChannel().sendMessageEmbeds(embedBuilder.build()).queue();
        }



        if (event.getMessage().getContentRaw().startsWith("!anime")) {
            MovieAPI.resetPageNum();
            String[] animeCommand = event.getMessage().getContentRaw().split(" ");

            if(animeCommand.length<2) {
                event.getChannel().sendMessage("What anime would you like to search for?").queue();
            }
            searchAnime = animeCommand[1];
            for (int i = 1; i < animeCommand.length; i++) {
                searchAnime += " " + animeCommand[i];
            }

            try {
                List<MovieAPI.Anime> animeList = MovieAPI.searchAnime(searchAnime);
                EmbedBuilder embedBuilder = new EmbedBuilder();
                for (int i = 0; i< animeList.size(); i++) {
                    MovieAPI.Anime anime1 = animeList.get(i);
                    embedBuilder.setTitle(anime1.getTitle());
                    embedBuilder.setDescription("Genres: " + anime1.getGenres() + "\n"+"Type: " + anime1.getType()
                            + "\n"+ "Episodes: "+ anime1.getEpisodes());
                    embedBuilder.setImage(anime1.getImage());

                    Button backButtonAnime = Button.success("backAnime", "back");
                    Button nextButtonAnime = Button.success("nextAnime", "next");
                    event.getChannel().sendMessageEmbeds(embedBuilder.build()).setActionRow(backButtonAnime, nextButtonAnime).queue();

                }
            } catch (Exception e) {
                event.getChannel().sendMessage("An error has occurred").queue();
            }

    }



        if (event.getMessage().getContentRaw().startsWith("!genre")) {
            MovieAPI.resetPageNum();
            String[] genreCommand = event.getMessage().getContentRaw().split(" ");
            if(genreCommand.length<2) {
                event.getChannel().sendMessage("What genre of anime would you like to search for?").queue();
            }
             searchGenre = genreCommand[1];

            try {
                List<MovieAPI.Anime> animeList = MovieAPI.searchGenre(searchGenre);
                EmbedBuilder embedBuilder = new EmbedBuilder();
                for (int i = 0; i< animeList.size(); i++) {
                    MovieAPI.Anime anime1 = animeList.get(i);
                    embedBuilder.setTitle(anime1.getTitle());
                    embedBuilder.setDescription("Genres: " + anime1.getGenres() + "\n"+"Type: " + anime1.getType()
                            + "\n"+ "Episodes: "+ anime1.getEpisodes());
                    embedBuilder.setImage(anime1.getImage());

                    Button backButtonGenre = Button.success("backgenre", "back");
                    Button nextButtonGenre = Button.success("nextgenre", "next");
                    event.getChannel().sendMessageEmbeds(embedBuilder.build()).setActionRow(backButtonGenre, nextButtonGenre).queue();
                    
                }
            } catch (Exception e) {
                event.getChannel().sendMessage("An error has occurred").queue();
            }

        }
    }







    // buttons that go to different pages of the api
    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        super.onButtonInteraction(event);
        // genre search buttons
        if (event.getButton().getId().equals("backgenre")) {
            movieAPI.prevPage();
            try {
                List<MovieAPI.Anime> animeList = MovieAPI.searchGenre(searchGenre);
                EmbedBuilder embedBuilder = new EmbedBuilder();
                for (int i = 0; i< animeList.size(); i++) {
                    MovieAPI.Anime anime1 = animeList.get(i);
                    embedBuilder.setTitle(anime1.getTitle());
                    embedBuilder.setDescription("Genres: " + anime1.getGenres() + "\n"+"Type: " + anime1.getType()
                            + "\n"+ "Episodes: "+ anime1.getEpisodes());
                    embedBuilder.setImage(anime1.getImage());
                    event.getMessage().editMessageEmbeds(embedBuilder.build()).queue(); // replaces the current embed when the button is pressed
                    event.deferEdit().queue();
                }
            } catch (Exception e) {
                event.getChannel().sendMessage("An error has occurred").queue();
            }
        } else if(event.getButton().getId().equals("nextgenre")) {
            movieAPI.nextPage();
            try {
                List<MovieAPI.Anime> animeList = MovieAPI.searchGenre(searchGenre);
                EmbedBuilder embedBuilder = new EmbedBuilder();
                for (int i = 0; i< animeList.size(); i++) {
                    MovieAPI.Anime anime1 = animeList.get(i);
                    embedBuilder.setTitle(anime1.getTitle());
                    embedBuilder.setDescription("Genres: " + anime1.getGenres() + "\n"+"Type: " + anime1.getType()
                            + "\n" + "Episodes: " + anime1.getEpisodes());
                    embedBuilder.setImage(anime1.getImage());
                    event.getMessage().editMessageEmbeds(embedBuilder.build()).queue(); // replaces the current embed when the button is pressed
                    event.deferEdit().queue();

        }
            }catch (Exception e) {
                event.getChannel().sendMessage("An error has occurred").queue();
            }
    }
        // anime search buttons
        if (event.getButton().getId().equals("backAnime")) {
            movieAPI.prevPage();
            try {
                List<MovieAPI.Anime> animeList = MovieAPI.searchAnime(searchAnime);
                EmbedBuilder embedBuilder = new EmbedBuilder();
                for (int i = 0; i< animeList.size(); i++) {
                    MovieAPI.Anime anime1 = animeList.get(i);
                    embedBuilder.setTitle(anime1.getTitle());
                    embedBuilder.setDescription("Genres: " + anime1.getGenres() + "\n"+"Type: " + anime1.getType()
                            + "\n"+ "Episodes: "+ anime1.getEpisodes());
                    embedBuilder.setImage(anime1.getImage());
                    event.getMessage().editMessageEmbeds(embedBuilder.build()).queue(); // replaces the current embed when the button is pressed
                    event.deferEdit().queue();
                }
            } catch (Exception e) {
                event.getChannel().sendMessage("An error has occurred").queue();
            }
        } else if(event.getButton().getId().equals("nextAnime")) {
            movieAPI.nextPage();
            try {
                List<MovieAPI.Anime> animeList = MovieAPI.searchAnime(searchAnime);
                EmbedBuilder embedBuilder = new EmbedBuilder();
                for (int i = 0; i< animeList.size(); i++) {
                    MovieAPI.Anime anime1 = animeList.get(i);
                    embedBuilder.setTitle(anime1.getTitle());
                    embedBuilder.setDescription("Genres: " + anime1.getGenres() + "\n"+"Type: " + anime1.getType()
                            + "\n" + "Episodes: " + anime1.getEpisodes());
                    embedBuilder.setImage(anime1.getImage());
                    event.getMessage().editMessageEmbeds(embedBuilder.build()).queue(); // replaces the current embed when the button is pressed
                    event.deferEdit().queue();

                }
            }catch (Exception e) {
                event.getChannel().sendMessage("An error has occurred").queue();
            }
        }

    }


}

