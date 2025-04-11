import { Component } from '@angular/core';

@Component({
  selector: 'app-footer',
  standalone: true,
  template: `
  <footer class="footer">
    <p><span class="emoji">💈</span> Danillo Oliveira - DECOLA TECH 2025 <span class="emoji">💈</span></p>
  </footer>
`,
styles: [`
  .footer {
    background: linear-gradient(90deg,rgba(0, 0, 0, 0.6) 0%, #333 50%,rgba(36, 36, 36, 0.6) 100%);
    color: #fff;
    text-align: center;
    padding: 11px 0;
    font-family: 'Amarante', serif;
    font-size: 16px;
    letter-spacing: 1px;
    position: fixed;
    bottom: 0;
    left: 0;
    right: 0;
    z-index: 1000;
    box-shadow: 0 -2px 10px rgba(0,0,0,0.5);
    border-top: 1px solid rgba(145, 145, 145, 0.25);
  }

  .footer p:hover{text-shadow: 0 0 12px rgba(250, 201, 40, 0.8);

  }

  .footer:hover{border-color: rgba(197, 197, 197, 0.3); 
}
  
  .emoji {
    font-size: 1.5em; 
    display: inline-block;
    vertical-align: middle;
    margin: 0 5px; /
  }
`]
})
export class FooterComponent {}