import { Component, OnInit } from '@angular/core';

declare var YT: any; 

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  player: any;
  currentSong = "Carregando...";
  videoIds = [
      { id: 'djV11Xbc914', title: 'a-ha - Take On Me' },
      { id: 'dQw4w9WgXcQ', title: 'Rick Astley - Never Gonna Give You Up' },
      { id: 'LeONcsS_euE', title: 'Bon Jovi - Livin On A Prayer' },
      { id: 'lDK9QqIzhwk', title: 'Michael Jackson - Billie Jean' },
      { id: 'Zi_XLOBDo_Y', title: 'Madonna - Like a Virgin' },
      { id: 'ujNeHIo7oTE', title: 'Wham! - Wake Me Up Before You Go-Go' },
      { id: 'SaolVEJEjV4', title: 'Cyndi Lauper - Girls Just Want To Have Fun' },
      { id: 'xFrGuyw1V8s', title: 'ABBA - Gimme! Gimme! Gimme!' },
      { id: 'booKP974B0k', title: 'Toto - Africa' },
      { id: 'GxBSyx85Kp8', title: 'Journey - Dont Stop Believin' },
      { id: 'Rbm6GXllBiw', title: 'Prince - Purple Rain' },
      { id: 'N6O2ncUKvlg', title: 'Queen - Another One Bites The Dust' },
      { id: 'HgzGwKwLmgM', title: 'Duran Duran - Hungry Like The Wolf' },
      { id: 'JmcA9LIIXWw', title: 'The Police - Every Breath You Take' },
      { id: 'N6O2ncUKvlg', title: 'Queen - Another One Bites The Dust' },
      { id: 'lAD6Obi7Cag', title: 'Guns N Roses - Sweet Child O Mine' },
      { id: 'fLexgOxsZu0', title: 'The Clash - Should I Stay or Should I Go' },
      { id: '1lyu1KKwC74', title: 'David Bowie - Lets Dance' },
      { id: 'pIgZ7gMze7A', title: 'The B-52s - Love Shack' },
      { id: 'xM1B-8Bkz1E', title: 'Blondie - Call Me' }
  ];
  currentVideoIndex = 0;

  ngOnInit() {
  
    const tag = document.createElement('script');
    tag.src = 'https://www.youtube.com/iframe_api';
    document.body.appendChild(tag);

    (window as any).onYouTubeIframeAPIReady = () => {
      this.createPlayer();
    };
  }

  createPlayer() {
    this.player = new YT.Player('youtube-player', {
      height: '200',
      width: '100%',
      videoId: this.videoIds[this.currentVideoIndex].id,
      playerVars: {
        autoplay: 1,
        mute: 0, 
        controls: 1,
        modestbranding: 1,
        rel: 0
      },
      events: {
        'onReady': (event: any) => event.target.playVideo(),
        'onStateChange': (event: any) => this.onPlayerStateChange(event)
      }
    });
  }

  onPlayerStateChange(event: any) {

    if (event.data === YT.PlayerState.ENDED) {
      this.playNextVideo();
    }
  }

  playNextVideo() {
    this.currentVideoIndex = (this.currentVideoIndex + 1) % this.videoIds.length;
    this.player.loadVideoById(this.videoIds[this.currentVideoIndex].id);
    this.currentSong = this.videoIds[this.currentVideoIndex].title;
  }

  getCurrentSong(): string {
    return this.currentSong;
  }
}


